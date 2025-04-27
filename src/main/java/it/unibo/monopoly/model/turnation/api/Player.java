package it.unibo.monopoly.model.turnation.api;

import java.awt.Color;

import it.unibo.monopoly.utils.Identifiable;

/**
 * Player interface.
 * Extends {@link Identifiable} for the identification of the players
*/
public interface Player extends Identifiable<Integer>{
    /**
     * @return the player's nickname
     */
    String getName();

    /**
     * @return the player's color
     */
    Color getColor();
}
