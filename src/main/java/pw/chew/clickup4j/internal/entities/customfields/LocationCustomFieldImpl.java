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

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.customfields.LocationCustomField;

public class LocationCustomFieldImpl extends CustomFieldImpl implements LocationCustomField {
    public LocationCustomFieldImpl(JSONObject data, ClickUp4j api) {
        super(data, api);
    }

    @Override
    @NotNull
    public String getValue() {
        return getValueObject().getString("formatted_address");
    }

    @Override
    public float getLatitude() {
        return getValueObject().getJSONObject("location").getFloat("lat");
    }

    @Override
    public float getLongitude() {
        return getValueObject().getJSONObject("location").getFloat("lng");
    }

    @Override
    public String getPlaceId() {
        return getValueObject().getJSONObject("location").getString("place_id");
    }

    private JSONObject getValueObject() {
        return getData().getJSONObject("value");
    }
}
