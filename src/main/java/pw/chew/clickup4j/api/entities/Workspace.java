package pw.chew.clickup4j.api.entities;

import java.awt.Color;

/**
 * A Workspace in ClickUp. This is the highest level of organization in ClickUp.
 */
public interface Workspace {
    String getId();

    String getName();

    Color getColor();

    String getAvatar();

    // TODO: Members
}
