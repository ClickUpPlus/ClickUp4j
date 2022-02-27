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
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.managers.TaskManager;
import pw.chew.clickup4j.internal.entities.TaskImpl;
import pw.chew.clickup4j.internal.requests.Requester;
import pw.chew.clickup4j.internal.requests.Route;

import java.util.function.Consumer;

public class TaskManagerImpl extends GenericManagerImpl implements TaskManager {
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

    public Requester<Task> save() {
        Request request = Route.Task.UPDATE_TASK
            .body(data).build(id)
            .addHeader("Authorization", api.getToken())
            .build();

        return new Requester<>(api.getHttpClient(), request, r -> new TaskImpl(new JSONObject(r), api));
    }

    public Task complete() {
        Request request = Route.Task.UPDATE_TASK
            .body(data).build(id)
            .addHeader("Authorization", api.getToken())
            .build();

        return new Requester<>(api.getHttpClient(), request, r -> new TaskImpl(new JSONObject(r), api)).complete();
    }
}
