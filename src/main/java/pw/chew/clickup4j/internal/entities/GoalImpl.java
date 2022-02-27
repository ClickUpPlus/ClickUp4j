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
import pw.chew.clickup4j.api.entities.Goal;
import pw.chew.clickup4j.api.entities.Target;
import pw.chew.clickup4j.api.entities.TimelineEntry;
import pw.chew.clickup4j.api.entities.User;

import java.awt.Color;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GoalImpl implements Goal {
    private final JSONObject data;
    private final ClickUp4j api;

    public GoalImpl(@NotNull JSONObject data, @NotNull ClickUp4j api) {
        this.data = data;
        this.api = api;
    }

    @Override
    public @NotNull UUID getId() {
        return UUID.fromString(data.getString("id"));
    }

    @Override
    public @NotNull String getPrettyId() {
        return data.getString("pretty_id");
    }

    @Override
    public @NotNull String getName() {
        return data.getString("name");
    }

    @Override
    public @NotNull String getWorkspaceId() {
        return data.getString("workspace_id");
    }

    @Override
    public @NotNull String getCreatorId() {
        return data.getString("creator_id");
    }

    @Override
    public @NotNull Color getColor() {
        return Color.decode(data.getString("color"));
    }

    @Override
    public @NotNull OffsetDateTime getDateCreated() {
        String date = data.getString("date_created");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public @Nullable OffsetDateTime getStartDate() {
        String date = data.optString("start_date", null);
        if (date == null) return null;

        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public @Nullable OffsetDateTime getDueDate() {
        String date = data.optString("due_date", null);
        if (date == null) return null;

        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public @NotNull String getDescription() {
        return data.getString("description");
    }

    @Override
    public boolean isPrivate() {
        return data.getBoolean("private");
    }

    @Override
    public boolean isArchived() {
        return data.getBoolean("archived");
    }

    @Override
    public boolean isMultipleOwners() {
        return data.getBoolean("multiple_owners");
    }

    @Override
    public @NotNull String getEditorToken() {
        return data.getString("editor_token");
    }

    @Override
    public @NotNull OffsetDateTime getDateUpdated() {
        String date = data.getString("date_updated");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public @NotNull List<User> getOwners() {
        return data.getJSONArray("owners").toList().stream().map(o -> new UserImpl((JSONObject) o, api)).collect(Collectors.toList());
    }

    @Override
    public @NotNull List<Target> getTargets() {
        return data.getJSONArray("key_results").toList().stream()
            .map(o -> new TargetImpl((JSONObject) o, api, this)).collect(Collectors.toList());
    }

    @Override
    public int getTargetCount() {
        return data.getInt("key_result_count");
    }

    @Override
    public float getPercentCompleted() {
        return data.getFloat("percent_completed");
    }

    @Override
    public @NotNull List<TimelineEntry> getHistory() {
        return data.getJSONArray("history").toList().stream()
            .map(o -> new TimelineEntryImpl((JSONObject) o, api, getTargets())).collect(Collectors.toList());
    }

    @Override
    public @NotNull String getPrettyUrl() {
        return data.getString("pretty_url");
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }
}
