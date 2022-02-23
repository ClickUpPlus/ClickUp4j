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
import java.util.List;

/**
 * <h2>Checklist</h2>
 *
 * <p>A checklist is a list of items that can be completed.
 * Every checklist can have items, and items can be completed.
 */
public interface Checklist {
    /**
     * The checklist's ID.
     *
     * @return The checklist's ID.
     */
    @NotNull
    String getId();

    /**
     * The ID of the task this checklist belongs to.
     *
     * @return The ID of the task this checklist belongs to.
     */
    @NotNull
    String getTaskId();

    /**
     * The checklist's name.
     *
     * @return The checklist's name.
     */
    @NotNull
    String getName();

    /**
     * Return when this checklist was created.
     *
     * @return When this checklist was created.
     */
    @NotNull
    OffsetDateTime getCreatedAt();

    /**
     * The ordering of this checklist, if there are multiple.
     * This can be used as a sorting key.
     *
     * @return The order index of this checklist.
     */
    int getOrderIndex();

    /**
     * Gets the ID of the user who created this checklist.
     *
     * @return the user ID of the user who created this checklist.
     */
    long getCreatorId();

    /**
     * The amount of items that are marked as complete.
     *
     * @return The amount of items that are marked as complete.
     */
    int getResolvedCount();

    /**
     * The amount of items that are marked as incomplete.
     *
     * @return The amount of items that are marked as incomplete.
     */
    int getUnresolvedCount();

    /**
     * Returns the items in this checklist.
     *
     * @return The items in this checklist.
     */
    List<Item> getItems();

    /**
     * An item in the checklist.
     */
    interface Item {
        /**
         * The item's ID.
         *
         * @return The item's ID.
         */
        @NotNull
        String getId();

        /**
         * The item's name.
         *
         * @return The item's name.
         */
        @NotNull
        String getName();

        /**
         * The item's ordering index.
         * This can be used as a sorting key.
         *
         * @return The item's ordering index.
         */
        double getOrderIndex();

        /**
         * This item's assignee.
         *
         * @return The item's assignee.
         */
        @Nullable
        User getAssignee();

        // TODO: Implement this!
        void getGroupAssignee();

        /**
         * Returns whether this item is marked as complete.
         *
         * @return Whether this item is marked as complete.
         */
        boolean isResolved();

        /**
         * The parent item of this checklist item.
         * <br>If this is null, this item is a top-level item.
         *
         * @return Nullable parent item of this checklist item.
         */
        @Nullable
        Item getParent();

        /**
         * Returns when this item was created.
         *
         * @return When this item was created.
         */
        @NotNull
        OffsetDateTime getCreatedAt();

        /**
         * All child items of this item. If the item has no children, this will be empty.
         *
         * @return Never-null list containing all child items of this item.
         */
        @NotNull
        List<Item> getChildren();
    }

    /**
     * Returns this {@link ClickUp4j} instance.
     *
     * @return This {@link ClickUp4j} instance.
     */
    ClickUp4j getClickUp4j();
}
