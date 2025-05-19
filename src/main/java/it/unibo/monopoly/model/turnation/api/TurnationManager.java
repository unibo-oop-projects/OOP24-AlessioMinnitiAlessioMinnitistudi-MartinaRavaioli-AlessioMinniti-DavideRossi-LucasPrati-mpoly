package it.unibo.monopoly.model.turnation.api;

import java.util.Collection;

/**
 * turnation manager interface.
*/
public interface TurnationManager {
    /**
     * @return if is over
     */
    boolean isOver();
    /**
     * @return the next player
     */
    Player getNextPlayer();
    /**
     * @return the dices' result
     */
    Collection<Integer> moveByDices();
    /**
     * return the id of the current player.
     * @return int
     */
    int getIdCurrPlayer();
    /**
     * return the current player.
     * @return Player
     */
    Player getCurrPlayer();
}
