package model.Turnation.impl;

import it.unibo.common.Pair;
import model.Turnation.api.Player;
import model.Turnation.api.TurnationManager;

public class TurnationManagerImpl implements TurnationManager {
    public boolean isOver() { return false; }
    public Player getNextPlayer() { return null; }
    public Pair<Integer, Integer> moveByDices() { return null; }
}
