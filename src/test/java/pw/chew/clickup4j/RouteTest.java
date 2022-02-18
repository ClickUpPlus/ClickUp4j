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

package pw.chew.clickup4j;

import org.junit.jupiter.api.Test;
import pw.chew.clickup4j.internal.requests.Route;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteTest {
    @Test
    public void testTasks() {
        Route route = Route.Task.GET_TASK;

        assertEquals(route.getMethod(), "GET");
        assertEquals("/task/123", route.buildPathVariables("123"));
        assertEquals("https://api.clickup.com/api/v2/task/123", route.buildUrl("123"));
    }
}
