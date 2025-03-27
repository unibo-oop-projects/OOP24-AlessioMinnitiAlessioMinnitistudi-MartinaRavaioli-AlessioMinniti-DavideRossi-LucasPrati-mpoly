package model.Turnation.api;

import it.unibo.common.Pair;

public interface TurnationManager {
    boolean isOver();
    Player getNextPlayer();
    Pair<Integer, Integer> moveByDices();
}
