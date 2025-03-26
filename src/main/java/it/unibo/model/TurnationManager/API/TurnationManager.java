package it.unibo.model.TurnationManager.API;

import it.unibo.common.Pair;
import it.unibo.model.Player.API.Player;

public interface TurnationManager {
    boolean isOver();
    Player getNextPlayer();
    Pair<Integer, Integer> moveByDices();
}
