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
import pw.chew.clickup4j.api.ClickUp4j;

import java.time.OffsetDateTime;

/**
 * <h2>Attachment</h2>
 *
 * Attachments can be uploaded to Tasks to help share information.
 */
public interface Attachment {
    /**
     * The ID of the attachment.
     *
     * @return never-null ID of the attachment.
     */
    @NotNull
    String getId();

    /**
     * The version of the attachment.
     *
     * @return never-null version of the attachment.
     */
    @NotNull
    String getVersion();

    /**
     * Returns the date of the attachment.
     * <br>Presumably this is when it was uploaded.
     *
     * @return never-null date of the attachment.
     */
    @NotNull
    OffsetDateTime getDate();

    /**
     * Returns the name of the attachment.
     *
     * @return never-null name of the attachment.
     */
    @NotNull
    String getTitle();

    /**
     * Returns the file extension of the attachment.
     *
     * @return never-null file extension of the attachment.
     */
    @NotNull
    String getExtension();

    /**
     * Returns the URL for the attachment.
     *
     * @return never-null URL for the attachment.
     */
    @NotNull
    String getUrl();

    /**
     * Returns this {@link ClickUp4j} instance.
     *
     * @return never-null {@link ClickUp4j} instance.
     */
    @NotNull
    ClickUp4j getClickUp4j();
}
