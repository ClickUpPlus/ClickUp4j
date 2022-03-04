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

package pw.chew.clickup4j.internal.entities.managers;

import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.User;
import pw.chew.clickup4j.api.entities.managers.TaskManager;
import pw.chew.clickup4j.internal.entities.TaskImpl;
import pw.chew.clickup4j.internal.requests.Requester;
import pw.chew.clickup4j.internal.requests.Route;

import java.time.Duration;
import java.util.function.Consumer;

public class TaskManagerImpl extends GenericManagerImpl<Task> implements TaskManager {
    private final ClickUp4j api;
    private final String id;
    private final JSONObject data = new JSONObject();

    public TaskManagerImpl(@NotNull ClickUp4j clickUp4j, @NotNull String id) {
        super(clickUp4j);
        this.api = clickUp4j;
        this.id = id;
    }

    @Override
    public TaskManager setName(@NotNull String name) {
        data.put("name", name);
        return this;
    }

    @Override
    public TaskManager setDescription(@Nullable String description) {
        data.put("description", description == null ? "" : description);
        return this;
    }

    @Override
    public TaskManager setStatus(Task.@NotNull Status status) {
        data.put("status", status.getStatus());
        return this;
    }

    @Override
    public TaskManager setPriority(Task.@NotNull Priority priority) {
        data.put("priority", priority.getPriority());
        return this;
    }

    @Override
    public TaskManager setParent(@NotNull Task task) {
        data.put("parent", task.getId());
        return this;
    }

    @Override
    public TaskManager setTimeEstimate(@NotNull Duration duration) {
        data.put("time_estimate", duration.toMillis());
        return this;
    }

    @Override
    public TaskManager addAssignee(@NotNull User user) {
        if (data.has("assignees")) {
            JSONObject assignees = data.getJSONObject("assignees");
            if (assignees.has("add")) {
                assignees.getJSONArray("add").put(user.getId());
            } else {
                assignees.put("add", new JSONArray().put(user.getId()));
            }
        } else {
            data.put("assignees", new JSONObject().put("add", new JSONArray().put(user.getId())));
        }

        return this;
    }

    @Override
    public TaskManager addAssignees(@NotNull User... users) {
        for (User user : users) {
            addAssignee(user);
        }
        return this;
    }

    @Override
    public TaskManager removeAssignee(@NotNull User user) {
        if (data.has("assignees")) {
            JSONObject assignees = data.getJSONObject("assignees");
            if (assignees.has("rem")) {
                assignees.getJSONArray("rem").put(user.getId());
            } else {
                assignees.put("rem", new JSONArray().put(user.getId()));
            }
        } else {
            data.put("assignees", new JSONObject().put("rem", new JSONArray().put(user.getId())));
        }

        return this;
    }

    @Override
    public TaskManager removeAssignees(@NotNull User... users) {
        for (User user : users) {
            removeAssignee(user);
        }
        return this;
    }

    @Override
    public void save(Consumer<Task> callback) {
        buildRequester().queue(callback);
    }

    @Override
    public Task complete() {
        return buildRequester().complete();
    }

    private Requester<Task> buildRequester() {
        Request request = Route.Task.UPDATE_TASK
            .body(data).build(id)
            .addHeader("Authorization", api.getToken())
            .build();

        return new Requester<>(api.getHttpClient(), request, r -> new TaskImpl(new JSONObject(r), api));
    }
}
