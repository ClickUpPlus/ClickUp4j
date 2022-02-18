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

import pw.chew.clickup4j.api.entities.User;

import java.util.List;

public interface UsersCustomField extends ICustomField {
    @Override
    List<User> getValue();

    /**
     * Whether this allows multiple values. If true, the value will be a single user.
     * <br>In the client, this is referred to as "Select multiple users," and this is actually inverted.
     *
     * @return Whether this allows multiple values.
     */
    boolean isSingleUser();

    /**
     * Whether guests may show up in this field.
     * <br>Guests are not {@link pw.chew.clickup4j.api.entities.Member Members} of the workspace,
     * but may have rights in the list or task.
     *
     * @return Whether guests may show up in this field.
     */
    boolean shouldIncludeGuests();

    /**
     * Whether teams may show up in the field response. If this is true, {@link #getValue()} may break.
     *
     * @return Whether teams may show up in the field response.
     */
    boolean includeTeams();

    /**
     * Whether the entire workspace can show up in the field response.
     * Normally, only members of the list this task is a part of can be here.
     *
     * @return Whether the entire workspace can show up in the field response.
     */
    boolean includeEntireWorkspace();

    @Override
    default CustomFieldType getType() {
        return CustomFieldType.USERS;
    }
}
