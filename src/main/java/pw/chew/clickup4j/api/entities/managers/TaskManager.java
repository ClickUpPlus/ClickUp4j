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
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.internal.requests.Requester;

/**
 * This manager class is used to interact with tasks.
 *
 * Save the task by calling {@link #save()}.
 */
public interface TaskManager extends GenericManager {
    /**
     * Changes the name of this task.
     *
     * @param name The new name of this task.
     * @return This task manager.
     */
    TaskManager setName(@NotNull String name);

    Requester<Task> save();

    Task complete();
}
