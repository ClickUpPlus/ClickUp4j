/*
 * Copyright 2022 Chew and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pw.chew.clickup4j.internal.entities;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Attachment;
import pw.chew.clickup4j.api.entities.Checklist;
import pw.chew.clickup4j.api.entities.Space;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.User;
import pw.chew.clickup4j.api.entities.customfields.ICustomField;
import pw.chew.clickup4j.internal.entities.customfields.CustomFieldImpl;
import pw.chew.clickup4j.internal.requests.Requester;
import pw.chew.clickup4j.internal.requests.Route;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TaskImpl implements Task {
    private final JSONObject data;
    private final ClickUp4j api;

    public TaskImpl(JSONObject data, ClickUp4j clickUp4j) {
        this.data = data;
        this.api = clickUp4j;
    }

    @Override
    public @NotNull String getId() {
        return data.getString("id");
    }

    @Override
    public @Nullable String getCustomId() {
        return data.getString("custom_id");
    }

    @Override
    public @NotNull String getName() {
        return data.getString("name");
    }

    @Override
    public String getTextContent() {
        return data.getString("content");
    }

    @Override
    public String getDescription() {
        return data.optString("description");
    }

    @Override
    public @NotNull Status getStatus() {
        return new StatusImpl(data.getJSONObject("status"));
    }

    @Override
    public String getOrderIndex() {
        return data.getString("orderindex");
    }

    @Override
    public OffsetDateTime getDateCreated() {
        String date = data.getString("date_created");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public OffsetDateTime getDateUpdated() {
        String date = data.getString("date_updated");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public OffsetDateTime getDateClosed() {
        if (data.isNull("date_closed")) return null;

        String date = data.getString("date_closed");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public User getCreator() {
        return new UserImpl(data.getJSONObject("creator"), api);
    }

    @Override
    @NotNull
    public List<User> getAssignees() {
        return data.getJSONArray("assignees").toList().stream().map(o -> new UserImpl((JSONObject) o, api)).collect(Collectors.toList());
    }

    @Override
    @NotNull
    public List<User> getWatchers() {
        return data.getJSONArray("watchers").toList().stream().map(o -> new UserImpl((JSONObject) o, api)).collect(Collectors.toList());
    }

    @Override
    public @NotNull List<Checklist> getChecklists() {
        return data.getJSONArray("checklists").toList().stream().map(o -> new ChecklistImpl((JSONObject) o, api)).collect(Collectors.toList());
    }

    @Override
    @Nullable
    public String getParentId() {
        return data.optString("parent");
    }

    @Override
    public Requester<Task> retrieveParent() {
        return getParentId() == null ? null : api.retrieveTask(getParentId());
    }

    @Override
    @NotNull
    public Priority getPriority() {
        if (data.isNull("priority")) return Priority.NONE;

        String priorityString = data.getJSONObject("priority").getString("id");
        int priority = Integer.parseInt(priorityString);

        for (Priority p : Priority.values()) {
            if (p.getPriority() == priority) {
                return p;
            }
        }
        return Priority.UNKNOWN;
    }

    @Override
    @Nullable
    public OffsetDateTime getDueDate() {
        String date = data.optString("due_date");
        if (date == null) return null;

        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    @Nullable
    public OffsetDateTime getStartDate() {
        String date = data.optString("start_date");
        if (date == null) return null;

        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public Duration getTimeEstimate() {
        long timeEstimate = data.getLong("time_estimate");
        return Duration.ofMillis(timeEstimate);
    }

    @Override
    public Duration getTimeSpent() {
        long timeSpent = data.getLong("time_spent");
        return Duration.ofMillis(timeSpent);
    }

    @Override
    public @NotNull List<ICustomField> getCustomFields() {
        return data.getJSONArray("custom_fields").toList().stream()
            .map(o -> {
                JSONObject field = (JSONObject) o;
                return new CustomFieldImpl(field, api);
            })
            .collect(Collectors.toList());
    }

    @Override
    public String getListId() {
        return data.getJSONObject("list").getString("id");
    }

    @Override
    public String getFolderId() {
        return data.getJSONObject("folder").getString("id");
    }

    @Override
    public String getSpaceId() {
        return data.getJSONObject("space").getString("id");
    }

    @Override
    public Requester<Space> retrieveSpace() {
        return api.retrieveSpace(getSpaceId());
    }

    @Override
    public Requester<Attachment> uploadAttachment(File file, String filename) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("attachment", filename,
                RequestBody.create(file, MediaType.parse("application/octet-stream")))
            .addFormDataPart("filename", filename)
            .build();

        Request.Builder builder = Route.Task.UPLOAD_ATTACHMENT.build(getId())
            .post(body);

        Function<String, Attachment> mapper = (String response) -> new AttachmentImpl(new JSONObject(response), api);

        return new Requester<>(api.getHttpClient(), builder.build(), mapper);
    }

    @Override
    @NotNull
    public String getUrl() {
        return data.getString("url");
    }

    @Override
    public Requester<Task> resolve() {
        return api.retrieveTask(getId());
    }

    public static class StatusImpl implements Status {
        private final JSONObject data;

        public StatusImpl(JSONObject data) {
            this.data = data;
        }

        @Override
        public String getId() {
            return data.getString("id");
        }

        @Override
        public String getStatus() {
            return data.getString("status");
        }

        @Override
        public Color getColor() {
            return Color.decode("#" + data.getString("color"));
        }

        @Override
        public int getOrderIndex() {
            return data.getInt("orderindex");
        }

        @Override
        public String getType() {
            return data.getString("type");
        }
    }
}
