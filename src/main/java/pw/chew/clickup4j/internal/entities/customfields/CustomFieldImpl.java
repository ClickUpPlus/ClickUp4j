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
import pw.chew.clickup4j.api.entities.customfields.CustomFieldType;
import pw.chew.clickup4j.api.entities.customfields.ICustomField;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CustomFieldImpl implements ICustomField {
    private final JSONObject data;
    private final ClickUp4j api;

    public CustomFieldImpl(JSONObject data, ClickUp4j api) {
        this.data = data;
        this.api = api;
    }

    @Override
    public String getId() {
        return data.getString("id");
    }

    @Override
    public String getName() {
        return data.getString("name");
    }

    @Override
    public Object getValue() {
        return data.getString("value");
    }

    @Override
    public CustomFieldType getType() {
        return CustomFieldType.UNKNOWN;
    }

    @Override
    public OffsetDateTime getTimeCreated() {
        String date = data.getString("date_created");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public boolean isRequired() {
        return data.getBoolean("required");
    }

    @Override
    public ClickUp4j getClickUp4j() {
        return api;
    }

    JSONObject getData() {
        return data;
    }

    JSONObject getTypeConfig() {
        return data.getJSONObject("type_config");
    }
}
