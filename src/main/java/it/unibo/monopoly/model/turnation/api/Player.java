package it.unibo.monopoly.model.turnation.api;

import java.awt.Color;

/**
 * player interface.
*/
public interface Player {
    /**
     * @return the player's nickname
     */
    String getName();

    /**
     * @return the player's color
     */
    Color getColor();
}
