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

package pw.chew.clickup4j.internal.entities;

import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Space;
import pw.chew.clickup4j.api.entities.Task;
import pw.chew.clickup4j.api.entities.TaskList;
import pw.chew.clickup4j.api.entities.User;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TaskListImpl implements TaskList {
    private final JSONObject data;
    private final ClickUp4j api;

    public TaskListImpl(JSONObject data, ClickUp4j clickUp4j) {
        this.data = data;
        this.api = clickUp4j;
    }

    @Override
    public String getId() {
        return data.getString("id");
    }

    @Override
    public String getName() {
        return data.getString("name");
    }

    @Override
    public int getOrderIndex() {
        return data.getInt("order_index");
    }

    @Override
    public String getContent() {
        return data.getString("content");
    }

    @Override
    public User getAssignee() {
        return null;
    }

    @Override
    public void getTaskCount() {

    }

    @Override
    public OffsetDateTime getDueDate() {
        return null;
    }

    @Override
    public boolean isDueDateTime() {
        return false;
    }

    @Override
    public OffsetDateTime getStartDate() {
        return null;
    }

    @Override
    public boolean isStartDateTime() {
        return false;
    }

    @Override
    public Space getSpace() {
        return null;
    }

    @Override
    public Space retrieveSpace() {
        return null;
    }

    @Override
    public String getInboundAddress() {
        return data.getString("inbound_address");
    }

    @Override
    public boolean isArchived() {
        return data.getBoolean("archived");
    }

    @Override
    public boolean overrideStatuses() {
        return data.getBoolean("override_statuses");
    }

    @Override
    public List<Task.Status> getStatuses() {
        return data.getJSONArray("statuses").toList()
            .stream().map(s -> (JSONObject) s).map(StatusImpl::new).collect(Collectors.toList());
    }

    @Override
    public String getPermissionLevel() {
        return null;
    }

    @Override
    public ClickUp4j getClickUp4j() {
        return api;
    }

    public static class StatusImpl implements Task.Status {
        private final JSONObject statusData;

        public StatusImpl(JSONObject statusData) {
            this.statusData = statusData;
        }

        @Override
        public String getId() {
            return statusData.getString("id");
        }

        @Override
        public String getStatus() {
            return statusData.getString("status");
        }

        @Override
        public Color getColor() {
            return Color.decode(statusData.getString("color"));
        }

        @Override
        public int getOrderIndex() {
            return statusData.getInt("orderindex");
        }

        @Override
        public String getType() {
            return statusData.getString("type");
        }
    }
}
