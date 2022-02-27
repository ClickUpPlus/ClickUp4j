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

package pw.chew.clickup4j.internal.requests;

import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Arrays;

public class Route {
    public static class Authorization {
        public static final Route GET_USER = new Route("GET", "/user");
    }

    public static class Goal {
        public static final Route GET_GOAL = new Route("GET", "/goal/:goal_id");
        public static final Route GET_GOALS = new Route("GET", "/team/:team_id/goal");
    }

    public static class List {
        public static final Route GET_TASKS = new Route("GET", "/list/:list_id/task");
    }

    public static class Task {
        public static final Route GET_TASK = new Route("GET", "/task/:task_id");
        public static final Route UPDATE_TASK = new Route("PUT", "/task/:task_id");
        public static final Route UPLOAD_ATTACHMENT = new Route("POST", "/task/:task_id/attachment");
        public static final Route DELETE = new Route("DELETE", "/task/:task_id");
    }

    public static class Space {
        public static final Route GET_SPACE = new Route("GET", "/space/:space_id");
    }

    public static class Webhook {
        public static final Route CREATE_WEBHOOK = new Route("POST", "/team/:team_id/webhook");
        public static final Route GET_WEBHOOKS = new Route("GET", "/team/:team_id/webhook");
        public static final Route UPDATE_WEBHOOK = new Route("PUT", "/webhook/:webhook_id");
        public static final Route DELETE_WEBHOOK = new Route("DELETE", "/webhook/:webhook_id");
    }

    public static class Workspace {
        public static final Route GET_WORKSPACES = new Route("GET", "/team");
    }

    private final String method;
    private final String path;
    private RequestBody body = null;

    private final String baseUrl = "https://api.clickup.com/api/v2";

    public Route(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getUrl() {
        return baseUrl + path;
    }

    public String getMethod() {
        return method;
    }

    public String buildPathVariables(String... values) {
        if (!path.contains(":")) return path;

        // Replace the path variables in the path with the values, e.g. :value => value[0]
        StringBuilder builder = new StringBuilder();
        String[] pathParts = path.split("/");
        for (String pathPart : pathParts) {
            if (pathPart.startsWith(":")) {
                builder.append(values[0]);
                values = Arrays.copyOfRange(values, 1, values.length);
            }
            else {
                builder.append(pathPart);
            }
            builder.append("/");
        }
        // If the last character is a /, remove it
        if (builder.charAt(builder.length() - 1) == '/') {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    public String buildUrl(String... values) {
        return baseUrl + buildPathVariables(values);
    }

    /**
     * Builds a request for this route with the given values.
     * @param values The values to use in the path variables.
     * @return The request builder.
     */
    public Request.Builder build(String... values) {
        return new Request.Builder()
            .url(buildUrl(values))
            .method(method, body);
    }

    public Route body(RequestBody body) {
        this.body = body;
        return this;
    }

    public Route body(@NotNull JSONObject body) {
        this.body = RequestBody.create(body.toString(), Requester.JSON);
        return this;
    }
}
