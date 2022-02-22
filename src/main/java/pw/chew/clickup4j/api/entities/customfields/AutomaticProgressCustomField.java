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

public interface AutomaticProgressCustomField extends ICustomField {
    /**
     * Returns the currently displayed value of the progress bar.
     * This is represented as a percentage, so values are between 0 and 1.
     *
     * @return the current progress
     */
    @Override
    Float getValue();

    boolean isSubTaskTrackingEnabled();

    boolean isChecklistTrackingEnabled();

    boolean isArchivedSubTaskTrackingEnabled();

    boolean isAssignedCommentTrackingEnabled();

    /**
     * Returns the value to return when there is nothing being tracked.
     * This returns a {@link CompleteOn} enum value.
     * You can use {@link CompleteOn#getAsString()} to see what the value is in the UI,
     * or use {@link CompleteOn#getAsInt()} to get the value as an integer.
     *
     * @return the value to return when there is nothing being tracked
     */
    CompleteOn getCompleteOn();

    boolean isSubtaskRollupEnabled();

    @Override
    default CustomFieldType getType() {
        return CustomFieldType.AUTOMATIC_PROGRESS;
    }

    enum CompleteOn {
        DISPLAY_0_COMPLETED("Display 0% completed", 1),
        DISPLAY_100_COMPLETED("Display 100% completed", 2),
        DISPLAY_100_WHEN_CLOSED("Display 100% when task is in a done status", 3);

        private final String value;
        private final int internalValue;

        CompleteOn(String value, int internalValue) {
            this.value = value;
            this.internalValue = internalValue;
        }

        public String getAsString() {
            return value;
        }

        public int getAsInt() {
            return internalValue;
        }
    }
}
