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
package pw.chew.clickup4j.internal;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.internal.entities.TaskImpl;
import pw.chew.clickup4j.internal.requests.Requester;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ClickUp4jImpl implements ClickUp4j {
    private final OkHttpClient client;
    private final String token;

    public ClickUp4jImpl(String token, OkHttpClient client) {
        this.token = token;
        this.client = client;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Requester<List<Task>> retrieveTasks(String listId) {
        Request request = new Request.Builder()
            .get()
            .url("https://api.clickup.com/api/v2/list/" + listId + "/task")
            .addHeader("Authorization", token)
            .build();

        Function<String, List<Task>> mapper = response -> {
            JSONArray jsonArray = new JSONArray(response);

            List<Task> tasks = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                tasks.add(new TaskImpl(jsonArray.getJSONObject(i), this));
            }

            return tasks;
        };

        return new Requester<>(client, request, mapper);
    }

    @Override
    public Task retrieveTask(String taskId) {
        // TODO implement
        return null;
    }
}
