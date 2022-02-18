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
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.util.List;
import java.util.UUID;

/**
 * <h2>Drop Down Custom Field</h2>
 *
 * <p>A drop down custom field can have multiple options, and is not unlike a status.
 * Each option can have a color associated with it.
 * <br>A custom field will have a value, nullable current value, and a list of possible options.
 */
public interface DropDownCustomField extends ICustomField {
    /**
     * Possibly null current value of the custom field.
     *
     * @return current value of the custom field
     */
    @Override
    @Nullable
    DropDownOption getValue();

    /**
     * The default value of the custom field, if it has one.
     *
     * @return default value of the custom field
     */
    DropDownOption getDefaultValue();

    /**
     * A placeholder. This doesn't seem to be used.
     *
     * @return the placeholder
     */
    @Nullable
    String getPlaceholder();

    /**
     * A list of possible options for the custom field.
     *
     * @return list of possible options
     */
    @NotNull
    List<DropDownOption> getOptions();

    @Override
    default CustomFieldType getType() {
        return CustomFieldType.DROP_DOWN;
    }

    /**
     * A drop-down option.
     */
    interface DropDownOption {
        /**
         * @return The ID of the option.
         */
        UUID getId();

        /**
         * @return The name of the option.
         */
        String getName();

        /**
         * @return The color of the option.
         */
        Color getColor();

        /**
         * @return The order index of the option.
         */
        int getOrderIndex();
    }
}
