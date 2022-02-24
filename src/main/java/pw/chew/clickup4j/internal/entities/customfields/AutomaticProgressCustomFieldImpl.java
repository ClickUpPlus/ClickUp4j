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
package pw.chew.clickup4j.internal.entities.customfields;

import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.customfields.AutomaticProgressCustomField;

public class AutomaticProgressCustomFieldImpl extends CustomFieldImpl implements AutomaticProgressCustomField {
    public AutomaticProgressCustomFieldImpl(JSONObject data, ClickUp4j api) {
        super(data, api);
    }

    @Override
    public Float getValue() {
        if (!getData().has("value")) return null;

        return getData().getJSONObject("value").getFloat("percent_complete");
    }

    @Override
    public boolean isSubTaskTrackingEnabled() {
        return isTrackingEnabledFor("subtasks");
    }

    @Override
    public boolean isChecklistTrackingEnabled() {
        return isTrackingEnabledFor("checklists");
    }

    @Override
    public boolean isArchivedSubTaskTrackingEnabled() {
        return isTrackingEnabledFor("archived_subtasks");
    }

    @Override
    public boolean isAssignedCommentTrackingEnabled() {
        return isTrackingEnabledFor("assigned_comment");
    }

    @Override
    public CompleteOn getCompleteOn() {
        int value = getTypeConfig().getInt("complete_on");

        for (CompleteOn p : CompleteOn.values()) {
            if (p.getAsInt() == value) {
                return p;
            }
        }
        return CompleteOn.UNKNOWN;
    }

    @Override
    public boolean isSubtaskRollupEnabled() {
        return getTypeConfig().getBoolean("subtask_rollup");
    }

    private boolean isTrackingEnabledFor(String type) {
        return getTypeConfig().getJSONObject("tracking").optBoolean(type, false);
    }
}
