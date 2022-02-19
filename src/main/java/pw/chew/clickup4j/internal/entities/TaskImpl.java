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
import pw.chew.clickup4j.api.entities.Space;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.User;
import pw.chew.clickup4j.internal.requests.Requester;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

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
        return data.getString("text_content");
    }

    @Override
    public String getDescription() {
        return data.getString("description");
    }

    @Override
    public Status getStatus() {
        return null;
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
        return null;
    }

    @Override
    public List<User> getAssignees() {
        return null;
    }

    @Override
    public List<User> getWatchers() {
        return null;
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
    public OffsetDateTime getDueDate() {
        return null;
    }

    @Override
    public OffsetDateTime getStartDate() {
        return null;
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
    public String getUrl() {
        return null;
    }

    @Override
    public Requester<Task> resolve() {
        return api.retrieveTask(getId());
    }
}
