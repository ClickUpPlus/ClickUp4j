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

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import pw.chew.clickup4j.api.ClickUp4j;
import pw.chew.clickup4j.api.entities.Attachment;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class AttachmentImpl implements Attachment {
    private final JSONObject data;
    private final ClickUp4j api;

    public AttachmentImpl(JSONObject data, ClickUp4j api) {
        this.data = data;
        this.api = api;
    }

    @Override
    public @NotNull String getId() {
        return data.getString("id");
    }

    @Override
    public @NotNull String getVersion() {
        return data.getString("version");
    }

    @Override
    public @NotNull OffsetDateTime getDate() {
        String date = data.getString("date");
        return Instant.ofEpochMilli(Long.parseLong(date)).atOffset(ZoneOffset.UTC);
    }

    @Override
    public @NotNull String getTitle() {
        return data.getString("title");
    }

    @Override
    public @NotNull String getExtension() {
        return data.getString("extension");
    }

    @Override
    public @NotNull String getUrl() {
        return data.getString("url");
    }

    @Override
    public @NotNull ClickUp4j getClickUp4j() {
        return api;
    }
}
