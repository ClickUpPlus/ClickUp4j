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

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.customfields.LabelsCustomField;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

public class LabelsCustomFieldImpl extends CustomFieldImpl implements LabelsCustomField {
    public LabelsCustomFieldImpl(JSONObject data, ClickUp4j api) {
        super(data, api);
    }

    @Override
    public @NotNull List<Label> getValue() {
        List<Object> labels = getData().getJSONArray("value").toList();

        return getTypeConfig().getJSONArray("options").toList().stream()
            .map(LabelImpl::new)
            .filter(label -> labels.contains(label.getId()))
            .collect(Collectors.toList());
    }

    @Override
    public @NotNull List<Label> getLabels() {
        return getTypeConfig().getJSONArray("options").toList().stream()
            .map(LabelImpl::new).collect(Collectors.toList());
    }

    public static class LabelImpl implements Label {
        private final JSONObject labelData;

        public LabelImpl(JSONObject data) {
            this.labelData = data;
        }

        public LabelImpl(Object data) {
            this.labelData = (JSONObject) data;
        }

        @Override
        public String getId() {
            return labelData.getString("id");
        }

        @Override
        public String getName() {
            return labelData.getString("name");
        }

        @Override
        public Color getColor() {
            return Color.decode(labelData.getString("color"));
        }
    }
}
