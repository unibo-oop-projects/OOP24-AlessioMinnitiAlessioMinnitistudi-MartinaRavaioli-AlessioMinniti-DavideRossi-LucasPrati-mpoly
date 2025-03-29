package it.unibo.monopoly.model.turnation.api;

import common.Pair;

public interface TurnationManager {
    boolean isOver();
    Player getNextPlayer();
    Pair<Integer, Integer> moveByDices();
}
