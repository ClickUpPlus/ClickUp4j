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
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Space;
import pw.chew.clickup4j.api.entities.Task;

import java.util.List;
import java.util.stream.Collectors;

public class SpaceImpl implements Space {
    private final JSONObject data;
    private final ClickUp4j api;

    public SpaceImpl(JSONObject data, ClickUp4j clickUp4j) {
        this.data = data;
        this.api = clickUp4j;
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
    public @NotNull List<Task.Status> getStatuses() {
        return data.getJSONArray("statuses").toList()
            .stream().map(s -> (JSONObject) s).map(TaskListImpl.StatusImpl::new).collect(Collectors.toList());
    }

    @Override
    public boolean isPrivate() {
        return data.getBoolean("is_private");
    }

    @Override
    public boolean isMultipleAssignees() {
        return data.getBoolean("multiple_assignees");
    }

    @Override
    public boolean isArchived() {
        return data.getBoolean("archived");
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }
}
