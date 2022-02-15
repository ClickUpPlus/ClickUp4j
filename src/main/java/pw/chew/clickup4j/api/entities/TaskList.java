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
