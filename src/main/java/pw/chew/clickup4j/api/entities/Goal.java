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

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <h2>Goals</h2>
 *
 * Goals on ClickUp are a way to track your progress towards a goal. From the docs:
 * <br>Plan, manage, and track - anything you can imagine! Link tasks and Lists into one central source of truth.
 *
 * <p>Learn more <a href="https://docs.clickup.com/en/articles/2689199-goals">on their site</a>.</p>
 */
public interface Goal {
    /**
     * The unique identifier for this goal.
     *
     * @return never-null UUID of this goal.
     */
    @NotNull
    UUID getId();

    /**
     * Returns the "pretty" ID of this goal.
     * <br>This is the ID displayed in the client, and appears to be incremental.
     *
     * @return never-null String of the "pretty" ID of this goal.
     */
    @NotNull
    String getPrettyId();

    /**
     * The name of this goal.
     *
     * @return never-null String of the name of this goal.
     */
    @NotNull
    String getName();

    /**
     * The ID of the workspace this goal is in.
     *
     * @return never-null ID of the workspace this goal is in.
     */
    @NotNull
    String getWorkspaceId();

    /**
     * The ID of the creator of this goal.
     *
     * @return never-null ID of the creator of this goal.
     */
    @NotNull
    String getCreatorId();

    /**
     * Returns the color of this Goal, as used in the client as a background color.
     *
     * @return never-null Color of this goal.
     */
    @NotNull
    Color getColor();

    /**
     * Returns the date this goal was created.
     *
     * @return never-null OffsetDateTime of the date this goal was created.
     */
    @NotNull
    OffsetDateTime getDateCreated();

    /**
     * Returns the start date of this goal. It may be null if a goal does not have a start date.
     *
     * @return nullable OffsetDateTime of the start date of this goal.
     */
    @Nullable
    OffsetDateTime getStartDate();

    /**
     * Returns the due date of this goal. It may be null if a goal does not have a due date.
     *
     * @return nullable OffsetDateTime of the due date of this goal.
     */
    @Nullable
    OffsetDateTime getDueDate();

    /**
     * Returns the description of this goal.
     *
     * @return never-null String of the description of this goal.
     */
    @NotNull
    String getDescription();

    /**
     * Whether this goal is private, meaning users must be invited to view it.
     *
     * @return true if this goal is private, false otherwise.
     */
    boolean isPrivate();

    /**
     * Whether this goal is archived, meaning it is no longer visible to users.
     *
     * @return true if this goal is archived, false otherwise.
     */
    boolean isArchived();

    /**
     * Whether this goal can have multiple owners.
     *
     * @return true if this goal can have multiple owners, false otherwise.
     */
    boolean isMultipleOwners();

    /**
     * Returns the token used to edit this goal.
     *
     * @return never-null String of the token used to edit this goal.
     */
    @NotNull
    String getEditorToken();

    /**
     * Returns when this goal was last updated.
     *
     * @return never-null OffsetDateTime of when this goal was last updated.
     */
    @NotNull
    OffsetDateTime getDateUpdated();

    /**
     * Returns a potentially empty-list of "owners" of this goal.
     * <br>If {@link #isMultipleOwners()} is false, this will be a list of at most one element.
     *
     * @return never-null List of the owners of this goal.
     */
    @NotNull
    List<User> getOwners();

    /**
     * Returns all the targets in this goal.
     * <br>This might be an empty list.
     *
     * @return never-null List of the targets in this goal.
     */
    @NotNull
    List<Target> getTargets();

    /**
     * Returns the target count.
     * <br>In theory, this is equal to targets.size(), but it is not guaranteed to be.
     *
     * @return int of the target count.
     */
    int getTargetCount();

    /**
     * Returns the current progress of this goal.
     *
     * @return never-null Progress of this goal.
     */
    float getPercentCompleted();

    /**
     * Returns the history for this goal.
     * <br>This contains created or changed {@link Target targets}.
     *
     * @return never-null List of the history for this goal.
     */
    @NotNull
    List<TimelineEntry> getHistory();

    /**
     * Returns the pretty URL of this goal.
     * <br>This is equal to
     * {@code "https://app.clickup.com/}{@link #getWorkspaceId() workspaceId}{@code /goals/}{@link #getPrettyId() prettyId}.
     *
     * @return never-null String of the pretty URL of this goal.
     */
    @NotNull
    String getPrettyUrl();

    /**
     * Returns this {@link ClickUp4j} instance.
     *
     * @return never-null ClickUp4j instance.
     */
    @NotNull
    ClickUp4j getClickUp4j();
}
