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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Space;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.User;
import pw.chew.clickup4j.api.entities.Webhook;
import pw.chew.clickup4j.api.entities.Workspace;
import pw.chew.clickup4j.internal.entities.SpaceImpl;
import pw.chew.clickup4j.internal.entities.TaskImpl;
import pw.chew.clickup4j.internal.entities.UserImpl;
import pw.chew.clickup4j.internal.entities.WebhookImpl;
import pw.chew.clickup4j.internal.entities.WorkspaceImpl;
import pw.chew.clickup4j.internal.requests.Requester;
import pw.chew.clickup4j.internal.requests.Route;
import pw.chew.clickup4j.internal.webhooks.WebhookServer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClickUp4jImpl implements ClickUp4j {
    private final OkHttpClient client;
    private final String token;

    private final WebhookServer webhookServer;
    private final String webhookEndpoint;
    private Webhook webhook;

    public ClickUp4jImpl(String token, OkHttpClient client, WebhookServer webhookServer, String webhookEndpoint) {
        this.token = token;
        this.client = client;
        this.webhookServer = webhookServer;
        this.webhookEndpoint = webhookEndpoint;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return client;
    }

    @Override
    public Requester<List<Task>> retrieveTasks(@NotNull String listId) {
        Request request = Route.List.GET_TASKS.build(listId)
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
    public Requester<Task> retrieveTask(@NotNull String taskId) {
        if (taskId.isEmpty()) {
            throw new IllegalArgumentException("Task ID cannot be empty");
        }

        Request request = Route.Task.GET_TASK.build(taskId)
            .addHeader("Authorization", token)
            .build();

        Function<String, Task> mapper = response -> {
            JSONObject jsonObject = new JSONObject(response);
            return new TaskImpl(jsonObject, this);
        };

        return new Requester<>(client, request, mapper);
    }

    @Override
    public Requester<Space> retrieveSpace(@NotNull String spaceId) {
        if (spaceId.isEmpty()) {
            throw new IllegalArgumentException("Task ID cannot be empty");
        }

        Request request = Route.Space.GET_SPACE.build(spaceId)
            .addHeader("Authorization", token)
            .build();

        Function<String, Space> mapper = response -> {
            JSONObject jsonObject = new JSONObject(response);
            return new SpaceImpl(jsonObject, this);
        };

        return new Requester<>(client, request, mapper);
    }

    @Override
    public Requester<List<Workspace>> retrieveWorkspaces() {
        Request request = Route.Workspace.GET_WORKSPACES.build()
            .addHeader("Authorization", token)
            .build();

        Function<String, List<Workspace>> mapper = response -> new JSONObject(response).getJSONArray("teams")
            .toList().stream()
            .map(jsonObject -> new WorkspaceImpl((JSONObject) jsonObject, this))
            .collect(Collectors.toList());

        return new Requester<>(client, request, mapper);
    }

    @Override
    public Requester<User> retrieveSelfUser() {
        Request request = Route.Authorization.GET_USER.build()
            .addHeader("Authorization", token)
            .build();

        Function<String, User> mapper = response -> new UserImpl(new JSONObject(response), this);

        return new Requester<>(client, request, mapper);
    }

    @Override
    public Requester<List<Webhook>> retrieveWebhooks(String workspaceId) {
        Request request = Route.Webhook.GET_WEBHOOKS.build(workspaceId)
            .addHeader("Authorization", token)
            .build();

        Function<String, List<Webhook>> mapper = response -> {
            JSONObject hooks = new JSONObject(response);
            List<Webhook> webhooks = new ArrayList<>();
            for (Object hook : hooks.getJSONArray("webhooks")) {
                JSONObject data = (JSONObject) hook;
                webhooks.add(new WebhookImpl(data, this));
            }
            return webhooks;
        };

        return new Requester<>(client, request, mapper);
    }

    @Override
    public void startWebhookServer(String workspaceId) {
        if (webhookServer == null || webhookEndpoint == null)
            throw new IllegalStateException("Webhook server is not configured");

        retrieveWebhooks(workspaceId).queue(webhooks -> {
            for (Webhook webhook : webhooks) {
                if (webhook.getEndpoint().equals(webhookEndpoint)) {
                    this.webhook = webhook;
                    break;
                }
            }

            if (this.webhook == null) {
                JSONObject jsonObject = new JSONObject()
                    .put("endpoint", webhookEndpoint)
                    .put("events", new JSONArray().put("*"));

                RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json; charset=utf-8"));

                Request request = Route.Webhook.CREATE_WEBHOOK
                    .body(body).build(workspaceId)
                    .addHeader("Authorization", token)
                    .build();

                new Requester<>(client, request, response -> new WebhookImpl(new JSONObject(response).getJSONObject("webhook"), this)).queue(responseHook -> {
                    this.webhook = responseHook;

                    webhookServer.setSecret(responseHook.getSecret());
                    webhookServer.start();
                });
            } else {
                webhookServer.setSecret(this.webhook.getSecret());
                webhookServer.start();
            }
        });
    }

    @Override
    public void stopWebhookServer() {
        webhook.delete().queue(unused -> webhookServer.stop());
    }
}
