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

package pw.chew.clickup4j.api.entities.managers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.User;

import java.time.Duration;
import java.util.function.Consumer;

/**
 * This manager class is used to interact with tasks.
 *
 * Save the task by calling {@link #save(Consumer)}.
 */
public interface TaskManager extends GenericManager<Task> {
    /**
     * Changes the name of this task.
     *
     * @param name The new name of this task.
     * @return This task manager.
     */
    TaskManager setName(@NotNull String name);

    /**
     * Changes the description of this task.
     * <br>Pass {@code null} or {@code ""} to remove the description.
     *
     * @param description The new description of this task.
     * @return This task manager.
     */
    TaskManager setDescription(@Nullable String description);

    /**
     * Sets the status of this task.
     *
     * @param status The new status of this task.
     * @return This task manager.
     */
    TaskManager setStatus(@NotNull Task.Status status);

    /**
     * Sets the priority of this task.
     *
     * @param priority The new priority of this task.
     * @return This task manager.
     */
    TaskManager setPriority(@NotNull Task.Priority priority);

    /**
     * Sets the parent task of this task.
     * <br>This converts the task to a subtask. <b>This process is not reversible.</b>
     *
     * @param task The parent task.
     * @return This task manager.
     */
    TaskManager setParent(@NotNull Task task);

    /**
     * Sets the time estimate of this task.
     *
     * @param duration The time estimate of this task.
     * @return This task manager.
     */
    TaskManager setTimeEstimate(@NotNull Duration duration);

    /**
     * Adds an assignee to this task.
     *
     * @param user The assignee to add.
     * @return This task manager.
     */
    TaskManager addAssignee(@NotNull User user);

    /**
     * Adds multiple assignees to this task.
     *
     * @param users The assignees to add.
     * @return This task manager.
     */
    TaskManager addAssignees(@NotNull User... users);

    /**
     * Removes an assignee from this task.
     *
     * @param user The assignee to remove.
     * @return This task manager.
     */
    TaskManager removeAssignee(@NotNull User user);

    /**
     * Removes multiple assignees from this task.
     *
     * @param users The assignees to remove.
     * @return This task manager.
     */
    TaskManager removeAssignees(@NotNull User... users);

    @Override
    void save(Consumer<Task> callback);

    @Override
    Task complete();
}
