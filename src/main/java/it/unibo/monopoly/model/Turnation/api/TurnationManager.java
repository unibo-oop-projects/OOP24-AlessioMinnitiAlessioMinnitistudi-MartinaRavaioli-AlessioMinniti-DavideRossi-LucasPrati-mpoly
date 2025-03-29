package it.unibo.monopoly.model.turnation.api;

import org.apache.commons.lang3.tuple.Pair;

public interface TurnationManager {
    /**
     * @return
     */
    boolean isOver();
    /**
     * @return
     */
    Player getNextPlayer();
    /**
     * @return
     */
    Pair<Integer, Integer> moveByDices();
}
