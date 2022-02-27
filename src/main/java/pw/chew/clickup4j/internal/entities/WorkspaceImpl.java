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
import pw.chew.clickup4j.api.entities.Member;
import pw.chew.clickup4j.api.entities.Workspace;
import pw.chew.clickup4j.internal.requests.Requester;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

public class WorkspaceImpl implements Workspace {
    private final JSONObject data;
    private final ClickUp4j api;

    public WorkspaceImpl(@NotNull JSONObject data, @NotNull ClickUp4j api) {
        this.data = data;
        this.api = api;
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
    public @NotNull Color getColor() {
        return Color.decode(data.getString("color"));
    }

    @Override
    public @Nullable String getAvatar() {
        return data.optString("avatar");
    }

    @Override
    public @NotNull List<Member> getMembers() {
        return data.getJSONArray("members").toList().stream().map(o -> new MemberImpl((JSONObject) o, api)).collect(Collectors.toList());
    }

    @Override
    public @NotNull Requester<List<Goal>> retrieveGoals() {
        return api.retrieveGoals(this.getId());
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }
}
