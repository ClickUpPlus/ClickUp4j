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
import pw.chew.clickup4j.api.entities.Target;
import pw.chew.clickup4j.api.entities.TimelineEntry;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

public class TimelineEntryImpl implements TimelineEntry {
    private final JSONObject data;
    private final ClickUp4j api;
    private final Target target;

    public TimelineEntryImpl(@NotNull JSONObject data, @NotNull ClickUp4j api, @NotNull Target target) {
        this.data = data;
        this.api = api;
        this.target = target;
    }

    public TimelineEntryImpl(@NotNull JSONObject data, @NotNull ClickUp4j api, @NotNull List<Target> targets) {
        this.data = data;
        this.api = api;

        for (Target target : targets) {
            if (target.getId().toString().equals(data.getString("key_result_id"))) {
                this.target = target;
                break;
            }
        }
        throw new IllegalArgumentException("Target not found");
    }

    @Override
    public @NotNull UUID getId() {
        return UUID.fromString(data.getString("id"));
    }

    @Override
    public @NotNull Target getTarget() {
        return target;
    }

    @Override
    public @NotNull String getUserId() {
        return data.getString("userid");
    }

    @Override
    public @NotNull String getNote() {
        return data.getString("note");
    }

    @Override
    public @NotNull OffsetDateTime getDateModified() {
        long date = data.getLong("date_created");
        return Instant.ofEpochMilli(date).atOffset(ZoneOffset.UTC);
    }

    @Override
    public @Nullable Integer getStepsTaken() {
        return data.isNull("steps_taken") ? null : data.getInt("steps_taken");
    }

    @Override
    public @Nullable Integer getStepsBefore() {
        return data.isNull("steps_before") ? null : data.getInt("steps_taken");
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }
}
