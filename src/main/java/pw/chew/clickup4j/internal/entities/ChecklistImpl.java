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
import pw.chew.clickup4j.api.entities.User;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class ChecklistImpl implements Checklist {
    private final JSONObject data;
    private final ClickUp4j api;

    public ChecklistImpl(@NotNull JSONObject data, @NotNull ClickUp4j api) {
        this.data = data;
        this.api = api;
    }

    @Override
    public @NotNull String getId() {
        return data.getString("id");
    }

    @Override
    public @NotNull String getTaskId() {
        return data.getString("task_id");
    }

    @Override
    public @NotNull String getName() {
        return data.getString("name");
    }

    @Override
    public @NotNull OffsetDateTime getCreatedAt() {
        String date = data.getString("date_created");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public int getOrderIndex() {
        return data.getInt("orderindex");
    }

    @Override
    public long getCreatorId() {
        return data.getLong("creator");
    }

    @Override
    public int getResolvedCount() {
        return data.getInt("resolved");
    }

    @Override
    public int getUnresolvedCount() {
        return data.getInt("unresolved");
    }

    @Override
    public List<Item> getItems() {
        return data.getJSONArray("items").toList().stream().map(o -> new ItemImpl((JSONObject) o)).collect(Collectors.toList());
    }

    @Override
    public ClickUp4j getClickUp4j() {
        return api;
    }

    public class ItemImpl implements Item {
        private final JSONObject data;
        private final Item parent;

        public ItemImpl(@NotNull JSONObject data) {
            this.data = data;
            this.parent = null;
        }

        public ItemImpl(@NotNull JSONObject data, @NotNull Item parent) {
            this.data = data;
            this.parent = parent;
        }

        @Override
        public @NotNull String getId() {
            return data.getString("id");
        }

        @Override
        public @NotNull String getName() {
            return data.getString("name");
        }

        @Override
        public double getOrderIndex() {
            return data.getDouble("orderindex");
        }

        @Override
        public @Nullable User getAssignee() {
            return new UserImpl(data.getJSONObject("assignee"), api);
        }

        @Override
        public void getGroupAssignee() {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public boolean isResolved() {
            return data.getBoolean("resolved");
        }

        @Override
        public @Nullable Item getParent() {
            return parent;
        }

        @Override
        public @NotNull OffsetDateTime getCreatedAt() {
            String date = data.getString("date_created");
            return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
        }

        @Override
        public @NotNull List<Item> getChildren() {
            return data.getJSONArray("items").toList().stream().map(o -> new ItemImpl((JSONObject) o, this)).collect(Collectors.toList());
        }
    }
}
