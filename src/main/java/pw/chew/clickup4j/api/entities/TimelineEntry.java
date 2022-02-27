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
import java.util.UUID;

/**
 * <h2>Timeline Entry</h2>
 *
 * This is used for the Timeline of a {@link Goal} or {@link Target}.
 */
public interface TimelineEntry {
    /**
     * Get the ID of this Timeline Entry.
     *
     * @return never-null ID of this Timeline Entry.
     */
    @NotNull
    UUID getId();

    /**
     * Gets the {@link Target} this Timeline Entry is for.
     *
     * @return never-null Target this Timeline Entry is for.
     */
    @NotNull
    Target getTarget();

    /**
     * Returns the {@link User} id who created this Timeline Entry.
     *
     * @return never-null User id who created this Timeline Entry.
     */
    @NotNull
    String getUserId();

    /**
     * The note of this entry as displayed to the user.
     * <br>If the user did not leave a note, this will be a blank String.
     *
     * @return never-null note of this entry.
     */
    @NotNull
    String getNote();

    /**
     * The date this entry was created (or last modified).
     *
     * @return never-null date this entry was created.
     */
    @NotNull
    OffsetDateTime getDateModified();

    /**
     * Returns the amount of steps taken caused by this entry.
     * <br>This will be null if the entry was not a step change.
     *
     * @return amount of steps taken caused by this entry.
     */
    @Nullable
    Integer getStepsTaken();

    /**
     * Returns the amount of steps before this entry occurred.
     * <br>This will be null if the entry was creating the {@link Target}.
     *
     * @return amount of steps before this entry occurred.
     */
    @Nullable
    Integer getStepsBefore();

    /**
     * Returns this {@link ClickUp4j} instance.
     *
     * @return never-null ClickUp4j instance.
     */
    @NotNull
    ClickUp4j getClickUp4j();
}
