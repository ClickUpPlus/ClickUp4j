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
import pw.chew.clickup4j.api.entities.customfields.ManualProgressCustomField;

public class ManualProgressCustomFieldImpl extends CustomFieldImpl implements ManualProgressCustomField {
    public ManualProgressCustomFieldImpl(JSONObject data, ClickUp4j api) {
        super(data, api);
    }

    @Override
    public String getValue() {
        if (!getData().has("value")) return null;

        return getData().getJSONObject("value").getString("value");
    }

    @Override
    public Float getPercentCompleted() {
        if (!getData().has("value")) return null;

        return getData().getJSONObject("value").getFloat("percent_completed");
    }

    @Override
    public int getStartValue() {
        return getTypeConfig().getInt("start_value");
    }

    @Override
    public int getEndValue() {
        return getTypeConfig().getInt("end_value");
    }
}
