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
package pw.chew.clickup4j.api.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pw.chew.clickup4j.api.ClickUp4j;

import java.time.OffsetDateTime;

/**
 * A member is a simple wrapper for a User. It may contain additional information.
 * Most of the time, you might just want {@link #getUser()}.
 */
public interface Member {
    /**
     * Returns the {@link User} sub-interface of this member.
     *
     * @return never-null {@link User} sub-interface
     */
    @NotNull
    User getUser();

    /**
     * The email of the user.
     *
     * @return never-null email
     */
    @NotNull
    String getEmail();

    /**
     * The "initials" for this user.
     * <br>This seems to be used internally in the API for users without a profile picture.
     *
     * @return never-null initials
     */
    @NotNull
    String getInitials();

    /**
     * The role for this user. This is an enum constant {@link Role} to store the possible values.
     *
     * @return never-null {@link Role}
     */
    @NotNull
    Role getRole();

    /**
     * Returns the custom role of this user, if any.
     * <br>Custom Roles require Business Plus Plan or above.
     *
     * @return nullable custom role
     */
    @Nullable
    String getCustomRole();

    /**
     * Returns when this user was last active.
     * <br>If the user has been invited, but has not accepted yet, this will be {@code null}.
     *
     * @return nullable last active date
     */
    @Nullable
    OffsetDateTime getLastActive();

    /**
     * Returns when this user joined the workspace.
     * <br>If the user has been invited, but has not accepted yet, this will be {@code null}.
     *
     * @return nullable join date
     */
    @Nullable
    OffsetDateTime getDateJoined();

    /**
     * Returns when this user was invited.
     * <br>All members have an invitation date, even if they have not accepted yet, and even if they're owner.
     *
     * @return never-null invitation date
     */
    @NotNull
    OffsetDateTime getDateInvited();

    /**
     * Returns the inviting user for this member.
     * <br>Owners were never invited, so this may be {@code null}.
     *
     * @return nullable inviting user
     */
    @Nullable
    User getInvitedBy();

    /**
     * Returns this {@link ClickUp4j} instance.
     *
     * @return never-null {@link ClickUp4j} instance
     */
    @NotNull
    ClickUp4j getClickUp4j();

    enum Role {
        /**
         * The owner of the workspace, and has full access to the workspace.
         */
        OWNER(1),
        /**
         * Has the ability to manage the workspace, invite new members, and even manage other admins, but not the owner.
         */
        ADMIN(2),
        /**
         * Has access to public spaces, docs, and dashboards.
         */
        MEMBER(3),
        /**
         * Cannot be added to spaces, and must be invited to lists, docs, or tasks.
         */
        GUEST(4),
        /**
         * Placeholder for a role unknown to this library.
         */
        UNKNOWN(-1),
        ;

        private final int value;

        Role(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
