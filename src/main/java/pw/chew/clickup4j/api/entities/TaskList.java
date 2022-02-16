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

import java.time.OffsetDateTime;
import java.util.List;

public interface TaskList {
    String getId();

    String getName();

    int getOrderIndex();

    String getContent();

    // TODO: Inner class for Status
    // TODO: Inner class for Priority

    User getAssignee();

    // TODO: Find actual return type
    void getTaskCount();

    OffsetDateTime getDueDate();

    boolean isDueDateTime();

    OffsetDateTime getStartDate();

    boolean isStartDateTime();

    // TODO: Folder

    Space getSpace();

    Space retrieveSpace();

    String getInboundAddress();

    boolean isArchived();

    boolean overrideStatuses();

    List<Task.Status> getStatuses();

    String getPermissionLevel();
}
