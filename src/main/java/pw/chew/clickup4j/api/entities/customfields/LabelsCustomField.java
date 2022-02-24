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

import java.awt.Color;
import java.util.List;

/**
 * <h2>Labels Custom Field</h2>
 *
 * Labels is very similar to drop down, except you can set multiple option values.
 */
public interface LabelsCustomField extends ICustomField {
    /**
     * Returns the list of labels for this task.
     *
     * @return a list of labels.
     */
    @Override
    @NotNull
    List<Label> getValue();

    /**
     * Returns all possible labels for this task.
     *
     * @return a list of labels.
     */
    @NotNull
    List<Label> getLabels();

    @Override
    default CustomFieldType getType() {
        return CustomFieldType.LABELS;
    }

    /**
     * A single label option.
     */
    interface Label {
        /**
         * Returns the UUID for this label.
         *
         * @return The UUID for this label.
         */
        String getId();

        /**
         * The name of this label as shown in the UI.
         *
         * @return the label name
         */
        String getName();

        /**
         * The color of this label as shown in the UI.
         *
         * @return the label color
         */
        Color getColor();
    }
}
