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

package pw.chew.clickup4j.api.entities.customfields;

import org.jetbrains.annotations.NotNull;

/**
 * <h2>Location Custom Field</h2>
 *
 * Represents a location of a task.
 */
public interface LocationCustomField extends ICustomField {
    /**
     * Returns the value of the formatted address. This is the address that is displayed in the UI.
     * <p>To actually get the latitude and longitude, use {@link #getLatitude()} and {@link #getLongitude()}.</p>
     *
     * @return the location
     */
    @Override
    @NotNull
    String getValue();

    /**
     * Get the latitude of the location.
     *
     * @return the latitude
     */
    float getLatitude();

    /**
     * Get the longitude of the location.
     *
     * @return the longitude
     */
    float getLongitude();

    /**
     * The place ID of the location.
     *
     * @return the place ID
     */
    String getPlaceId();

    @Override
    default CustomFieldType getType() {
        return CustomFieldType.LOCATION;
    }
}
