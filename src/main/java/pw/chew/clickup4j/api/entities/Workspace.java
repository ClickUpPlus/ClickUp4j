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
import pw.chew.clickup4j.internal.requests.Requester;

import java.awt.Color;
import java.util.List;

/**
 * <h2>Workspace</h2>
 *
 * This is the highest level of organization in ClickUp.
 */
public interface Workspace {
    /**
     * The ID of the workspace.
     *
     * @return The ID of the workspace.
     */
    @NotNull
    String getId();

    /**
     * The human-readable name of the {@link Workspace}.
     *
     * @return Never-null String containing the workspace's name.
     */
    @NotNull
    String getName();

    /**
     * The color for this workspace.
     *
     * @return Never-null Color object containing the workspace's color.
     */
    @NotNull
    Color getColor();

    /**
     * The avatar or icon for this workspace.
     * <p>If the workspace does not have an avatar, this will be null.
     *
     * @return Nullable String containing the workspace's avatar.
     */
    @Nullable
    String getAvatar();

    /**
     * Returns a list of all the {@link Member members} in this workspace.
     *
     * @return Never-null List of {@link Member members} in this workspace.
     */
    @NotNull
    List<Member> getMembers();

    /**
     * Retrieves a list of {@link Goal goals} in this workspace.
     *
     * @return Never-null List of {@link Goal goals} in this workspace.
     */
    @NotNull
    Requester<List<Goal>> retrieveGoals();

    /**
     * Returns this ClickUp4j instance.
     *
     * @return Never-null ClickUp4j instance.
     */
    @NotNull
    ClickUp4j getClickUp4j();
}
