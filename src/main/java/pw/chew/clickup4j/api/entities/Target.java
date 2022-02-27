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
 * <h2>Goal Targets</h2>
 *
 * In ClickUp, goals can have targets (internally known as {@code key results}).
 * This helps track the overall progress of a goal.
 */
public interface Target {
    /**
     * The unique identifier of the target.
     *
     * @return never-null UUID of the target
     */
    @NotNull
    UUID getId();

    /**
     * The {@link Goal} this target belongs to.
     *
     * @return never-null {@link Goal} this target belongs to
     */
    @NotNull
    Goal getGoal();

    /**
     * Returns the name of the target.
     *
     * @return never-null name of the target
     */
    @NotNull
    String getName();

    /**
     * Returns the ID of the {@link User} who created the target.
     *
     * @return never-null ID of the {@link User} who created the target
     */
    @NotNull
    String getCreatorId();

    /**
     * Returns the owner of the target.
     *
     * @return nullable {@link User} owner of the target
     */
    @Nullable
    User getOwner();

    /**
     * Returns the type of the target.
     *
     * @return never-null type of the target
     */
    @NotNull
    String getType();

    /**
     * Returns the date and time when the target was created.
     *
     * @return never-null date and time when the target was created
     */
    @NotNull
    OffsetDateTime getDateCreated();

    /**
     * The number of steps to start with.
     *
     * @return number of steps to start with
     */
    int getStepsStart();

    /**
     * The number of steps to complete.
     *
     * @return number of steps to complete
     */
    int getStepsEnd();

    /**
     * Returns the current number of steps.
     *
     * @return current number of steps
     */
    int getCurrentStep();

    /**
     * Returns the unit of measurement for a step.
     *
     * @return never-null unit of measurement for a step
     */
    @NotNull
    String getUnit();

    /**
     * Returns the current percentage of completion.
     *
     * @return current percentage of completion
     */
    float getPercentCompleted();

    /**
     * Returns if this target is completed.
     *
     * @return true if this target is completed, false otherwise
     */
    boolean isCompleted();

    /**
     * Returns the most recent {@link TimelineEntry action} that was performed on the target.
     *
     * @return never-null {@link TimelineEntry} most recent action performed on the target
     */
    @NotNull
    TimelineEntry getLastAction();

    /**
     * Returns this {@link ClickUp4j} instance.
     *
     * @return never-null ClickUp4j instance.
     */
    @NotNull
    ClickUp4j getClickUp4j();
}
