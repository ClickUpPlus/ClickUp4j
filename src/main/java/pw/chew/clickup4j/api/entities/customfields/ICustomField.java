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

import java.time.OffsetDateTime;

/**
 * Interface for custom fields. All custom fields class extend this, and may have more methods.
 */
public interface ICustomField {
    /**
     * Returns the ID of the custom field.
     *
     * @return an ID of the custom field
     */
    String getId();

    /**
     * Returns the name of this Custom Field, which is displayed to the user.
     *
     * @return the name of this Custom Field
     */
    String getName();

    /**
     * Returns the value of this custom field.
     * <p>It's highly recommended to cast this value to the appropriate type,
     * because it returns more appropriate data.
     * For example, if the custom field is of type {@link CustomFieldType#TEXT},
     * you can cast the value to {@link TextCustomField}, this class can be found
     * using {@link CustomFieldType#getAssociatedClass()}.</p>
     * <p>This will be null under the following circumstances:
     * <ul>
     *     <li>No value is specified.</li>
     *     <li>The task response did not contain a {@code value}. Notably when an individual task is retrieved.</li>
     * </ul>
     *
     * @return the value of this custom field
     */
    Object getValue();

    /**
     * Returns the type of this custom field. This will be one of {@link CustomFieldType}.
     *
     * @return the type of this custom field
     */
    CustomFieldType getType();

    /**
     * Returns when this custom field was created.
     *
     * @return when this custom field was created
     */
    OffsetDateTime getTimeCreated();
}
