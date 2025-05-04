package it.unibo.monopoly.model.turnation.api;

import org.apache.commons.lang3.tuple.Pair;

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
    Pair<Integer, Integer> moveByDices();
    void setOver();
}
