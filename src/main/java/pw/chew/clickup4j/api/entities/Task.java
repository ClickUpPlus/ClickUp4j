package pw.chew.clickup4j.api.entities;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.List;

public interface Task {
    String getId();

    String getCustomId();

    String getName();

    String getTextContent();

    String getDescription();

    Status getStatus();

    String getOrderIndex();

    OffsetDateTime getDateCreated();

    OffsetDateTime getDateUpdated();

    OffsetDateTime getDateClosed();

    User getCreator();

    // TODO: Confirm return type
    List<User> getAssignees();

    // TODO: Checklists
    // TODO: Tags

    // TODO: Confirm return type
    Task getParent();

    // TODO: Make this an enum
    int getPriority();

    OffsetDateTime getDueDate();

    OffsetDateTime getStartDate();

    // TODO: Find actual return type
    void getTimeEstimate();

    // TODO: Find actual return type
    void getTimeSpent();

    // TODO: Custom Fields

    String getListId();

    // TODO: Retrieve List

    String getFolderId();

    // TODO: Retrieve Folder

    String getSpaceId();

    Space retrieveSpace();

    String getUrl();

    /**
     * Represents a status of a task.
     */
    interface Status {
        String getStatus();

        Color getColor();

        int getOrderIndex();

        String getType();
    }
}
