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
import pw.chew.clickup4j.api.entities.User;

import java.awt.Color;

public class UserImpl implements User {
    private final JSONObject data;
    private final ClickUp4j api;

    public UserImpl(JSONObject data, ClickUp4j api) {
        this.data = data;
        this.api = api;
    }

    @Override
    public long getId() {
        return data.getLong("id");
    }

    @Override
    public @NotNull String getUsername() {
        return data.getString("username");
    }

    @Override
    public @NotNull Color getColor() {
        return Color.decode(data.getString("color"));
    }

    @Override
    public @NotNull String getProfilePicture() {
        return data.getString("profile_picture");
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }
}
