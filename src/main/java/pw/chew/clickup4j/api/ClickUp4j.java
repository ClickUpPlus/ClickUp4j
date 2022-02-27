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
package pw.chew.clickup4j.api;

import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import pw.chew.clickup4j.api.entities.Goal;
import pw.chew.clickup4j.api.entities.Space;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.User;
import pw.chew.clickup4j.api.entities.Webhook;
import pw.chew.clickup4j.api.entities.Workspace;
import pw.chew.clickup4j.internal.requests.Requester;

import java.util.List;

/**
 * The core of ClickUp4j. All parts of the API can be accessed starting from this class
 */
public interface ClickUp4j {
    /**
     * Gets the token currently being used for requests.
     *
     * @return the token
     */
    String getToken();

    /**
     * Returns the OkHttpClient used for requests.
     *
     * @return the OkHttpClient
     */
    OkHttpClient getHttpClient();

    /**
     * Returns a list of tasks in a specified list ID.
     *
     * @param listId the list ID
     * @return the list of tasks
     */
    Requester<List<Task>> retrieveTasks(@NotNull String listId);

    /**
     * Retrieves a single task by ID. This can be from any workspace the user has access to.
     * <br>This will <b>NOT</b> return values for the custom fields.
     * <br>Custom IDs are not supported with this method.
     *
     * @param taskId the task ID
     * @return the task
     */
    Requester<Task> retrieveTask(@NotNull String taskId);

    /**
     * Retrieves a space by ID. This can be from any workspace the user has access to.
     *
     * @param spaceId the space ID
     * @return the space
     */
    Requester<Space> retrieveSpace(@NotNull String spaceId);

    /**
     * Retrieves a list of workspaces the currently authenticated user can access.
     *
     * @return the list of workspaces
     */
    Requester<List<Workspace>> retrieveWorkspaces();

    /**
     * Retrieve a specific {@link Goal goal} by ID.
     *
     * @param goalId the goal ID
     * @return the goal
     */
    Requester<Goal> retrieveGoal(String goalId);

    /**
     * Retrieves a list of {@link Goal goals} for the specified {@link Workspace workspace}.
     *
     * @param workspaceId the workspace ID
     * @return the list of goals
     */
    Requester<List<Goal>> retrieveGoals(String workspaceId);

    /**
     * Retrieves the currently logged-in User.
     * <br>The user is workspace agnostic.
     *
     * @return the user
     */
    Requester<User> retrieveSelfUser();

    /**
     * Retrieves a list of {@link Webhook webhooks} for the specified {@link Workspace workspace}.
     *
     * @param workspaceId the workspace ID
     * @return the list of webhooks
     */
    Requester<List<Webhook>> retrieveWebhooks(String workspaceId);

    /**
     * Starts a webhook server with the settings provided from the {@link ClickUp4jBuilder}.
     * <br>This method will do the following:
     * <ol>
     *     <li>Retrieve existing webhooks</li>
     *     <li>If a webhook with the endpoint exists, do nothing.</li>
     *     <li>Otherwise, create a new webhook with the endpoint.</li>
     * </ol>
     * <br><b>Be sure to stop the server when you're done with {@link #stopWebhookServer()}.</b>
     *
     * @param workspaceId the workspace ID
     */
    void startWebhookServer(String workspaceId);

    /**
     * Shuts down the webhook server.
     * <br>This method will also delete the webhook from ClickUp.
     */
    void stopWebhookServer();
}
