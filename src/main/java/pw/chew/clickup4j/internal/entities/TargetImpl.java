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

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class TargetImpl implements Target {
    private final JSONObject data;
    private final ClickUp4j api;
    private final Goal goal;

    public TargetImpl(@NotNull JSONObject data, @NotNull ClickUp4j api, @NotNull Goal goal) {
        this.data = data;
        this.api = api;
        this.goal = goal;
    }

    @Override
    public @NotNull UUID getId() {
        return UUID.fromString(data.getString("id"));
    }

    @Override
    public @NotNull Goal getGoal() {
        return goal;
    }

    @Override
    public @NotNull String getName() {
        return data.getString("name");
    }

    @Override
    public @NotNull String getCreatorId() {
        return data.getString("creator");
    }

    @Override
    public @Nullable User getOwner() {
        if (data.isNull("owner")) return null;

        return new UserImpl(data.getJSONObject("owner"), api);
    }

    @Override
    public @NotNull String getType() {
        return data.getString("type");
    }

    @Override
    public @NotNull OffsetDateTime getDateCreated() {
        String date = data.getString("date_created");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public int getStepsStart() {
        return data.getInt("steps_start");
    }

    @Override
    public int getStepsEnd() {
        return data.getInt("steps_end");
    }

    @Override
    public int getCurrentStep() {
        return data.getInt("steps_current");
    }

    @Override
    public @NotNull String getUnit() {
        return data.getString("unit");
    }

    @Override
    public float getPercentCompleted() {
        return data.getFloat("percent_completed");
    }

    @Override
    public boolean isCompleted() {
        return data.getBoolean("completed");
    }

    @Override
    public @NotNull TimelineEntry getLastAction() {
        return new TimelineEntryImpl(data.getJSONObject("last_action"), api, this);
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }
}
