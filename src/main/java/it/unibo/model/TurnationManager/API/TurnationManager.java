package it.unibo.model.TurnationManager.API;

import it.unibo.common.Pair;

public interface TurnationManager {
    boolean isOver();
    Player getNextPlayer();
    Pair<Integer, Integer> moveByDices();
}
