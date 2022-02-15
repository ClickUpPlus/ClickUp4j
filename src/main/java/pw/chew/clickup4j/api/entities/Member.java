package pw.chew.clickup4j.api.entities;

import java.time.OffsetDateTime;

/**
 * A member is a simple wrapper for a User. It may contain additional information.
 * Most of the time, you might just want {@link #getUser()}.
 */
public interface Member {
    User getUser();

    String getEmail();

    String getInitials();

    int getRole();

    String getCustomRole();

    OffsetDateTime getLastActive();

    OffsetDateTime getDateJoined();

    OffsetDateTime getDateInvited();
}
