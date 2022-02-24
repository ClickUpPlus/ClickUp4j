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
import pw.chew.clickup4j.api.entities.customfields.ICustomField;
import pw.chew.clickup4j.internal.requests.Requester;

import java.awt.Color;
import java.io.File;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * <h2>ClickUp Task</h2>
 *
 * <p>Tasks are the heart of ClickUp, and are the lowest level of organization in the hierarchy.
 * <br><a href="https://docs.clickup.com/en/articles/856274-task-ids">View the ClickUp Docs for more info.</a>
 */
public interface Task {
    /**
     * The unique ID of this task.
     * <p>All IDs are unique on ClickUp, so this can safely be used as a primary key.
     *
     * @return the ID of this task
     */
    @NotNull
    String getId();

    /**
     * The custom ID for this task.
     * <p>This feature is exclusive to the <i>Business</i> plan or above, so this is likely to be null.
     *
     * @return A possibly null custom ID for this task
     */
    @Nullable
    String getCustomId();

    /**
     * The name of this task. This is also referred to as the title.
     *
     * @return the name of this task
     */
    @NotNull
    String getName();

    /**
     * Returns the text content of this task.
     * <br>The API seems to return the same as {@link #getDescription()}.
     *
     * @return the description of this task
     * @see #getDescription()
     */
    String getTextContent();

    /**
     * Returns the complete description of this task.
     *
     * @return the description of this task
     */
    @Nullable
    String getDescription();

    /**
     * Returns the status of this task.
     *
     * @return the status of this task
     */
    @NotNull
    Status getStatus();

    /**
     * Returns the order index of this task.
     * <br>The higher the index, the lower (vertically) the task will be.
     *
     * @return the order index of this task
     */
    String getOrderIndex();

    /**
     * Returns when this Task was created.
     * <br>If this task was imported, it may show up as a date prior to ClickUp's creation date. Pretty neat!
     *
     * @return the date this task was created
     */
    @NotNull
    OffsetDateTime getDateCreated();

    /**
     * Returns when this Task was last modified.
     *
     * @return the date this task was last modified
     */
    @NotNull
    OffsetDateTime getDateUpdated();

    /**
     * Returns when this Task was closed.
     * <br>If the task is not closed, this will be null.
     *
     * @return the date this task was closed, or null if it is not closed
     */
    @Nullable
    OffsetDateTime getDateClosed();

    /**
     * Returns the user who created this task.
     *
     * @return the user who created this task
     */
    User getCreator();

    /**
     * Returns a list of {@link User users} who are assigned to work on this task.
     * <br>If the space doesn't have {@link Space#isMultipleAssignees()} enabled, this will return a list of one user.
     *
     * @return a list of users assigned to work on this task
     */
    @NotNull
    List<User> getAssignees();

    /**
     * Returns the watchers for this task.
     * This list will be empty if this task was retrieved from
     * {@link pw.chew.clickup4j.api.ClickUp4j#retrieveTasks(String)}, but will show up if this task was retrieved from
     * {@link pw.chew.clickup4j.api.ClickUp4j#retrieveTask(String)}.
     *
     * @return the watchers for this task
     */
    @NotNull
    List<User> getWatchers();

    /**
     * Returns a list of checklists associated with this task.
     * <br>This list will be empty if this task has no checklists.
     *
     * @return a list of checklists associated with this task
     */
    @NotNull
    List<Checklist> getChecklists();

    // TODO: Tags

    /**
     * Retrieves the parent ID of this task.
     * <p>This will be null if this task is a top-level task.
     * <br>
     * To get the parent task, use {@link Task#retrieveParent()}.
     *
     * @return the parent ID of this task
     */
    @Nullable
    String getParentId();

    /**
     * Retrieves the parent task of this task.
     * <p>This will be null if this task is a top-level task.
     *
     * @return a requester to get the parent task
     */
    Requester<Task> retrieveParent();

    /**
     * The priority of this task. If there is no priority set, this will return {@link Priority#NONE}.
     *
     * @return the never-null priority of this task
     */
    @NotNull
    Priority getPriority();

    /**
     * Returns the due date of this task, if there is one.
     * <br>If there is no due date, this will return null.
     *
     * @return the possibly-null due date of this task
     */
    @Nullable
    OffsetDateTime getDueDate();

    /**
     * Returns the start date of this task, if there is one.
     * <br>If there is no start date, this will return null.
     *
     * @return the possibly-null start date of this task
     */
    @Nullable
    OffsetDateTime getStartDate();

    /**
     * Returns the estimated time to complete this task.
     *
     * @return the estimated time to complete this task
     */
    Duration getTimeEstimate();

    /**
     * Returns the time tracked to this task.
     * <br>This requires the space to have the {@code Time Tracking} ClickApp enabled.
     *
     * @return the time spent on this task
     */
    Duration getTimeSpent();

    /**
     * Returns all the custom fields for this task.
     * <br>Custom Fields may not have a value set.
     *
     * @return all the custom fields for this task
     */
    @NotNull
    List<ICustomField> getCustomFields();

    String getListId();

    // TODO: Retrieve List

    String getFolderId();

    // TODO: Retrieve Folder

    String getSpaceId();

    Requester<Space> retrieveSpace();

    /**
     * Uploads the specified {@link File} to this task.
     *
     * @param file the file to upload
     * @param filename the name of the file
     * @return a requester to upload an attachment to this task
     */
    Requester<Attachment> uploadAttachment(File file, String filename);

    /**
     * The URL link to this task.
     *
     * @return the URL link to this task
     */
    @NotNull
    String getUrl();

    /**
     * Resolves missing fields in this task.
     * <br>Due to API limitations, this excludes custom field values.
     *
     * @return this task with all missing fields resolved
     */
    Requester<Task> resolve();

    /**
     * Represents a status of a task.
     */
    interface Status {
        String getId();

        String getStatus();

        Color getColor();

        int getOrderIndex();

        String getType();
    }

    /**
     * Represents the priority of a task.
     */
    enum Priority {
        URGENT(1, "#f50000", "Urgent"),
        HIGH(2, "#ffcc00", "High"),
        NORMAL(3, "#6fddff", "Normal"),
        LOW(4, "#d8d8d8", "Low"),
        NONE(0, null, "None"),
        UNKNOWN(-1, null, "Unknown"),
        ;

        private final int priority;
        private final String color;
        private final String name;

        Priority(int priority, String color, String name) {
            this.priority = priority;
            this.color = color;
            this.name = name;
        }

        /**
         * Returns the int representation of this priority.
         * <br>The order index, if needed, is the same as the int representation.
         *
         * @return the int representation of this priority
         */
        public int getPriority() {
            return priority;
        }

        /**
         * Returns the color of this priority as displayed in the UI.
         * <br><b>This will be {@code null} for {@link #NONE}</b>
         *
         * @return the color of this priority
         */
        public Color getColor() {
            return Color.decode(color);
        }

        /**
         * The name of this priority.
         *
         * @return the name of this priority
         */
        public String getName() {
            return name;
        }
    }
}
