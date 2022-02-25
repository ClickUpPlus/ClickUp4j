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
import pw.chew.clickup4j.api.entities.Member;
import pw.chew.clickup4j.api.entities.User;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class MemberImpl implements Member {
    private final JSONObject data;
    private final ClickUp4j api;

    public MemberImpl(JSONObject data, ClickUp4j api) {
        this.data = data;
        this.api = api;
    }

    @Override
    public @NotNull User getUser() {
        return new UserImpl(data.getJSONObject("user"), api);
    }

    @Override
    public @NotNull String getEmail() {
        return getUserData().getString("email");
    }

    @Override
    public @NotNull String getInitials() {
        return getUserData().getString("initials");
    }

    @Override
    public @NotNull Role getRole() {
        int role = getUserData().getInt("role");

        for (Role r : Role.values()) {
            if (r.getValue() == role) {
                return r;
            }
        }

        return Role.UNKNOWN;
    }

    @Override
    @Nullable
    public String getCustomRole() {
        return getUserData().optString("custom_role", null);
    }

    @Override
    public OffsetDateTime getLastActive() {
        String date = data.optString("last_active", null);
        if (date == null) return null;

        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public OffsetDateTime getDateJoined() {
        String date = data.optString("date_joined", null);
        if (date == null) return null;

        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public @NotNull OffsetDateTime getDateInvited() {
        String date = data.getString("date_invited");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public @Nullable User getInvitedBy() {
        if (data.has("invited_by")) return null;

        return new UserImpl(data.getJSONObject("invited_by"), api);
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }

    private JSONObject getUserData() {
        return data.getJSONObject("user");
    }
}
