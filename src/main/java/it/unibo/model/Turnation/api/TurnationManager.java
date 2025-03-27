package it.unibo.model.Turnation.api;

import it.unibo.common.Pair;
import it.unibo.model.Player.API.Player;

public interface TurnationManager {
    boolean isOver();
    Player getNextPlayer();
    Pair<Integer, Integer> moveByDices();
}
