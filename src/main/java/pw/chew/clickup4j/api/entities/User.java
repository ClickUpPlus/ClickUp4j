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

/**
 * <h2>ClickUp User</h2>
 *
 * The user entity represents a user in the ClickUp system.
 * <br>
 * This class is a little weird due to inconsistencies in the ClickUp API.
 * From what I could gather, these are the fields that appear in every reference to a user.
 * Member may have additional fields, but when they show up is infrequent.
 */
public interface User {
    /**
     * The unique ID of the user.
     *
     * @return the user ID
     */
    long getId();

    /**
     * This user's username. This is what is displayed in the ClickUp UI.
     *
     * @return the username
     */
    @NotNull
    String getUsername();

    /**
     * The color for this user.
     *
     * @return the color
     */
    @NotNull
    Color getColor();

    /**
     * Returns a link to this user's profile picture.
     * <br>This will be {@code null} if the user has no profile picture.
     *
     * @return the possibly-null profile picture link
     */
    @Nullable
    String getProfilePicture();

    /**
     * Returns this ClickUp4j instance.
     *
     * @return the ClickUp4j instance
     */
    @NotNull
    ClickUp4j getClickUp4j();
}
