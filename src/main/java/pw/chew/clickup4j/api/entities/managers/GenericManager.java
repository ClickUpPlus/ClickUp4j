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
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.internal.requests.Requester;

import java.util.function.Consumer;

/**
 * A generic manager for ClickUp entities.
 */
public interface GenericManager<T> {
    /**
     * Returns the {@link ClickUp4j} instance.
     *
     * @return The {@link ClickUp4j} instance.
     */
    @NotNull
    ClickUp4j getClickUp4j();

    /**
     * Saves the entity.
     * <br>Pass a consumer to be called when the request is complete.
     *
     * @param consumer The consumer to be called when the request is complete.
     */
    void save(Consumer<T> consumer);

    /**
     * Synchronously saves the entity.
     *
     * @return The saved entity.
     */
    T complete();
}
