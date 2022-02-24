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

package pw.chew.clickup4j.internal.entities.customfields;

import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.User;
import pw.chew.clickup4j.api.entities.customfields.UsersCustomField;
import pw.chew.clickup4j.internal.entities.UserImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersCustomFieldImpl extends CustomFieldImpl implements UsersCustomField {
    public UsersCustomFieldImpl(JSONObject data, ClickUp4j api) {
        super(data, api);
    }

    @Override
    public List<User> getValue() {
        if (!getData().has("value")) return new ArrayList<>();

        return getData().getJSONArray("value").toList().stream().map(o -> new UserImpl((JSONObject) o, getClickUp4j())).collect(Collectors.toList());
    }

    @Override
    public boolean isSingleUser() {
        return getTypeConfig().getBoolean("single_user");
    }

    @Override
    public boolean shouldIncludeGuests() {
        return getTypeConfig().getBoolean("include_guests");
    }

    @Override
    public boolean includeTeams() {
        return getTypeConfig().getBoolean("include_groups");
    }

    @Override
    public boolean includeEntireWorkspace() {
        return getTypeConfig().getBoolean("include_team_members");
    }
}
