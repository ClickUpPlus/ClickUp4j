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

public interface ManualProgressCustomField extends ICustomField {
    /**
     * Gets the currently shown progress bar value.
     * If you want the percent completed, use {@link #getPercentCompleted()}.
     *
     * @return The current progress bar value.
     */
    @Override
    String getValue();

    /**
     * Returns the overall percent completed for this custom field.
     * This is essentially
     * ({@link #getValue()} - {@link #getStartValue()}) / ({@link #getEndValue()} - {@link #getStartValue()}).
     *
     * @return The percent completed.
     */
    float getPercentCompleted();

    /**
     * This is the minimum possible for the progress bar.
     *
     * @return The minimum value.
     */
    int getStartValue();

    /**
     * This is the maximum possible for the progress bar.
     *
     * @return The maximum value.
     */
    int getEndValue();

    @Override
    default CustomFieldType getType() {
        return CustomFieldType.MANUAL_PROGRESS;
    }
}
