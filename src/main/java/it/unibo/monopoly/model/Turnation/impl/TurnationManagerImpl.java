package it.unibo.monopoly.model.Turnation.impl;

import common.Pair;
import it.unibo.monopoly.model.Turnation.api.Player;
import it.unibo.monopoly.model.Turnation.api.TurnationManager;

public class TurnationManagerImpl implements TurnationManager {
    public boolean isOver() { return false; }
    public Player getNextPlayer() { return null; }
    public Pair<Integer, Integer> moveByDices() { return null; }
}
