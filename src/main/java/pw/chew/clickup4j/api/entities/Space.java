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
import pw.chew.clickup4j.api.ClickUp4j;

import java.util.List;

/**
 * Represents a space in ClickUp.
 * <br>A space is the 2nd level of organization in ClickUp.
 * It can contain lists, folders, and docs.
 */
public interface Space {
    /**
     * Returns the ID of this space.
     *
     * @return Never-null String containing the Id.
     */
    @NotNull
    String getId();

    /**
     * The human-readable name of the {@link Space}.
     *
     * @return Never-null String containing the space's name.
     */
    @NotNull
    String getName();

    /**
     * Returns a list of space-level {@link Task} statuses.
     * <br>Lists and folders may have their own statuses.
     *
     * @return Never-null List of {@link Task.Status} objects.
     */
    @NotNull
    List<Task.Status> getStatuses();

    /**
     * Checks if this space is private, meaning members must be invited to see it.
     *
     * @return True if this space is private, false otherwise.
     */
    boolean isPrivate();

    /**
     * Returns if the Multiple Assignees feature is enabled for this space.
     *
     * @return true if enabled, false otherwise
     */
    boolean isMultipleAssignees();

    /**
     * Returns if this Space is archived, meaning it is no longer visible to users.
     *
     * @return true if archived, false otherwise
     */
    boolean isArchived();

    /**
     * Returns the {@link ClickUp4j} instance of this Space.
     *
     * @return the corresponding instance
     */
    @NotNull
    ClickUp4j getClickUp4j();
}
