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

import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Webhook;
import pw.chew.clickup4j.internal.requests.Requester;
import pw.chew.clickup4j.internal.requests.Route;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class WebhookImpl implements Webhook {
    private final JSONObject data;
    private final ClickUp4j api;

    public WebhookImpl(@NotNull JSONObject data, @NotNull ClickUp4j api) {
        this.data = data;
        this.api = api;
    }

    @Override
    public @NotNull String getId() {
        return data.getString("id");
    }

    @Override
    public int getUserId() {
        return data.getInt("userid");
    }

    @Override
    public int getWorkspaceId() {
        return data.getInt("team_id");
    }

    @Override
    public @NotNull String getEndpoint() {
        return data.getString("endpoint");
    }

    @Override
    public @NotNull String getClientId() {
        return data.getString("client_id");
    }

    @Override
    public @NotNull List<String> getEvents() {
        return data.getJSONArray("events").toList().stream().map(Object::toString).collect(Collectors.toList());
    }

    @Override
    public @Nullable String getTaskId() {
        return data.optString("task_id", null);
    }

    @Override
    public @Nullable String getListId() {
        return data.optString("list_id", null);
    }

    @Override
    public @Nullable String getFolderId() {
        return data.optString("folder_id", null);
    }

    @Override
    public @Nullable String getSpaceId() {
        return data.optString("space_id", null);
    }

    @Override
    public Health getHealth() {
        return Health.valueOf(data.getJSONObject("health").getString("status").toUpperCase(Locale.ROOT));
    }

    @Override
    public int getFailCount() {
        return data.getJSONObject("health").getInt("fail_count");
    }

    @Override
    public @NotNull String getSecret() {
        return data.getString("secret");
    }

    @Override
    public Requester<Void> delete() {
        Request request = Route.Webhook.DELETE_WEBHOOK.build(getId())
            .addHeader("Authorization", getClickUp4j().getToken())
            .build();

        return new Requester<>(getClickUp4j().getHttpClient(), request, r -> null);
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }
}
