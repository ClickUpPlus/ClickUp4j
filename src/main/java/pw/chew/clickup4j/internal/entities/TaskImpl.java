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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Checklist;
import pw.chew.clickup4j.api.entities.Space;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.User;
import pw.chew.clickup4j.internal.requests.Requester;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
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
        return data.getJSONArray("checlists").toList().stream().map(o -> new ChecklistImpl((JSONObject) o, api)).collect(Collectors.toList());
    }

    @Override
    public Task getParent() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
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
    public String getListId() {
        return null;
    }

    @Override
    public String getFolderId() {
        return null;
    }

    @Override
    public String getSpaceId() {
        return null;
    }

    @Override
    public Requester<Space> retrieveSpace() {
        return api.retrieveSpace(getSpaceId());
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
