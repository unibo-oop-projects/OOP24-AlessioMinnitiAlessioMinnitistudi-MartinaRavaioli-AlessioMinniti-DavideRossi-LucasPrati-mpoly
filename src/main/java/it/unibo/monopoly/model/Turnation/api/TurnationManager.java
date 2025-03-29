package it.unibo.monopoly.model.turnation.api;

import org.apache.commons.lang3.tuple.Pair;

public interface TurnationManager {
    boolean isOver();
    Player getNextPlayer();
    Pair<Integer, Integer> moveByDices();
}
