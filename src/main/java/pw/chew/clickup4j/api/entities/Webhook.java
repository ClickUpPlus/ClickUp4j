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

import java.util.List;

/**
 * <h2>Webhook</h2>
 *
 * <p>A webhook is a URL that is called when a certain event happens.</p>
 */
public interface Webhook {
    /**
     * The ID of the webhook.
     *
     * @return never-null webhook ID
     */
    @NotNull
    String getId();

    /**
     * The user ID of the user who created the webhook.
     *
     * @return user ID
     */
    int getUserId();

    /**
     * The workspace this webhook belongs to.
     *
     * @return workspace ID
     */
    int getWorkspaceId();

    /**
     * The endpoint ClickUp will send webhooks to.
     *
     * @return endpoint URL
     */
    @NotNull
    String getEndpoint();

    /**
     * The client id of the webhook.
     *
     * @return client ID
     */
    @NotNull
    String getClientId();

    /**
     * The events this webhook is listening for.
     *
     * @return never-null, but potentially empty, list of events
     */
    @NotNull
    List<String> getEvents();

    /**
     * Returns the {@link Task} ID of the task this webhook is listening for.
     * <br>This may be null, meaning the webhook is either listening elsewhere, or globally.
     *
     * @return task ID, or null
     */
    @Nullable
    String getTaskId();

    /**
     * Returns the {@link TaskList} ID of the list this webhook is listening for.
     * <br>This may be null, meaning the webhook is either listening elsewhere, or globally.
     *
     * @return list ID, or null
     */
    @Nullable
    String getListId();

    /**
     * Returns the folder ID of the folder this webhook is listening for.
     * <br>This may be null, meaning the webhook is either listening elsewhere, or globally.
     *
     * @return folder ID, or null
     */
    @Nullable
    String getFolderId();

    /**
     * Returns the {@link Space} ID of the list this webhook is listening for.
     * <br>This may be null, meaning the webhook is either listening elsewhere, or globally.
     *
     * @return space ID, or null
     */
    @Nullable
    String getSpaceId();

    /**
     * Returns the Health of the webhook.
     * <br>This is one of the enum constants:
     * {@link Health#ACTIVE active}, {@link Health#FAILING failing}, or {@link Health#SUSPENDED suspended}.
     * <br>If the webhook is {@link Health#FAILING failing}, you can see the fail count with {@link #getFailCount()}.
     *
     * @return never-null health
     */
    Health getHealth();

    /**
     * Returns the number of times the webhook has failed.
     * <br>{@code 0} means the webhook is {@link Health#ACTIVE active}.
     *
     * @return fail count
     */
    int getFailCount();

    /**
     * Returns the secret of the webhook, to be used when verifying the webhook.
     *
     * @return never-null secret
     */
    @NotNull
    String getSecret();

    /**
     * Delete this webhook.
     *
     * @return nothing
     */
    Requester<Void> delete();

    enum Health {
        /**
         * From the ClickUp API:
         * <p>
         * If your webhook is sending us healthy http status codes,
         * we will keep sending your subscribed changes to the endpoint.
         */
        ACTIVE,
        /**
         * From the ClickUp API docs:
         * <p>
         * If an unsuccessful http status code is received or if a request takes longer than 15 seconds to complete,
         * we will consider the webhook as failing.
         * We understand though that flukes can happen, so we will retry your webhook five times for each event.
         * If on the fifth retry the endpoint is still failing,
         * we will increment a {@code fail_count} which you can see in the webhook {@code health} object.
         * This count will be incremented for each event that we fail to send you.
         * <br>If at any time while your webhook is marked as {@code failing} the issues with your endpoint are resolved
         * and success status codes are received,
         * we will automatically mark your webhook back to the {@code active} status and clear the {@code fail_count}.
         */
        FAILING,
        /**
         * From the ClickUp API docs:
         * <p>
         * If your webhook is set as failing and the fail count reaches 100, we will mark the webhook as suspended.
         * We will no longer attempt to send events to this webhook.
         */
        SUSPENDED,
    }

    /**
     * This ClickUp4j instance.
     *
     * @return never-null instance
     */
    @NotNull
    ClickUp4j getClickUp4j();
}
