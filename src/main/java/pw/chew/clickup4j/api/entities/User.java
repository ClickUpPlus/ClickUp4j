package pw.chew.clickup4j.api.entities;

import java.awt.Color;
import java.time.OffsetDateTime;

public interface User {
    long getId();

    String getUsername();

    Color getColor();

    String getProfilePicture();
}
