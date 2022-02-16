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
package pw.chew.clickup4j.api.entities.customfields;

/**
 * Represents a custom field.
 */
public enum CustomFieldType {
    /**
     * Represents a URL field. However, the URL is not validated.
     */
    URL(0),
    /**
     * Represents a drop-down field. Type Config consists of a list of options, with a default and placeholder.
     */
    DROP_DOWN(1, true),
    /**
     * Represents an email field.
     */
    EMAIL(2),
    /**
     * Represents a phone field.
     */
    PHONE(3),
    /**
     * Represents a date field. This field is actually validated and returns an OffsetDateTime if cast to
     * a DateCustomField.
     * @see {@link DateCustomField}
     */
    DATE(4),
    /**
     * Represents a text field.
     */
    TEXT(5),
    /**
     * Represents a checkbox field.
     */
    CHECKBOX(6),
    /**
     * Represents a number field.
     */
    NUMBER(7),
    /**
     * Represents a currency field.
     * Type Config refers to the precision, type, and default of the currency.
     */
    CURRENCY(8, true),
    /**
     * Represents a task field, may return a task.
     */
    TASKS(9),
    /**
     * Represents a user field, may return a user.
     */
    USERS(10),
    /**
     * Represents an emoji, specifically a rating of emoji, the type config gives the emoji and amount (1-5).
     */
    EMOJI(11, true),
    /**
     * Represents a label field. Type Config is the labels.
     */
    LABELS(13, true),
    /**
     * Represents an automatic progress field. Type Config is the progress qualifiers.
     */
    AUTOMATIC_PROGRESS(14, true),
    /**
     * Represents a manual progress field. Type Config is the range.
     */
    MANUAL_PROGRESS(15, true),
    /**
     * Represents a short text field.
     */
    SHORT_TEXT(16),
    /**
     * Represents a new field unknown to this library.
     */
    UNKNOWN(-1),
    ;

    private final int ordinal;
    private final boolean hasTypeConfig;

    CustomFieldType(int ordinal) {
        this.ordinal = ordinal;
        this.hasTypeConfig = false;
    }

    CustomFieldType(int ordinal, boolean hasTypeConfig) {
        this.ordinal = ordinal;
        this.hasTypeConfig = hasTypeConfig;
    }

    /**
     * Returns if this field has custom type config. It's recommended to case the {@link ICustomField}
     * to the correct type.
     *
     * @return true if this field has custom type config, false otherwise.
     */
    public boolean hasTypeConfig() {
        return hasTypeConfig;
    }

    /**
     * Returns the associated class with this custom field.
     *
     * @return A class extending {@link ICustomField}
     */
    public Class<? extends ICustomField> getAssociatedClass() {
        switch (this) {
            case URL:
                return URLCustomField.class;
            case DROP_DOWN:
                return DropDownCustomField.class;
            case EMAIL:
                return EmailCustomField.class;
            case PHONE:
                return PhoneCustomField.class;
            case DATE:
                return DateCustomField.class;
            case TEXT:
                return TextCustomField.class;
            case CHECKBOX:
                return CheckboxCustomField.class;
            case NUMBER:
                return NumberCustomField.class;
            case CURRENCY:
                return CurrencyCustomField.class;
            case TASKS:
                return TasksCustomField.class;
            case USERS:
                return UsersCustomField.class;
            case EMOJI:
                return EmojiCustomField.class;
            case LABELS:
                return LabelsCustomField.class;
            case AUTOMATIC_PROGRESS:
                return AutomaticProgressCustomField.class;
            case MANUAL_PROGRESS:
                return ManualProgressCustomField.class;
            case SHORT_TEXT:
                return ShortTextCustomField.class;
            default:
                return null;
        }
    }
}
