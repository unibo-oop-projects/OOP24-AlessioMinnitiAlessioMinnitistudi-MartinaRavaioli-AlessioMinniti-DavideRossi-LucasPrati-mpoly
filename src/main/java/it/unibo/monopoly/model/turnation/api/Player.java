package it.unibo.monopoly.model.turnation.api;

import java.awt.Color;
import java.util.Collection;

import it.unibo.monopoly.utils.api.Identifiable;

/**
 * Player interface.
 * Extends {@link Identifiable} for the identification of the players
*/
public interface Player extends Identifiable<Integer> {

    /**
     * @return the player's nickname
     */
    String getName();

    /**
     * @return the player's color
     */
    Color getColor();

    /**
     * @return if is alive
     */

    boolean isAlive();

    /**
     * tells whether and how long the player is parked.
     * @return if the player is parked
     */
    boolean isParked();

    /**
     * put the player in parked status and set the turns it has to wait.
     */
    void park();

    /**
     * tells whether the player is in prison.
     * @return whether the player is in prison 
     */
    boolean isInPrison();

    /**
     * put the player in prison and set the turns he has to wait.
     */
    void putInPrison();

    /**
     * tells if the player can exit 
     * the prison based on the dices result. 
     * If he can then it moves the players pawn
     * @param dice to get the result
     * @return whether he can
     */
    boolean canExitPrison(Collection<Integer> dice);

    /**
     * gets how many turns in prison the player has left.
     * @return the number of turns
     */
    int turnLeftInPrison();
}
