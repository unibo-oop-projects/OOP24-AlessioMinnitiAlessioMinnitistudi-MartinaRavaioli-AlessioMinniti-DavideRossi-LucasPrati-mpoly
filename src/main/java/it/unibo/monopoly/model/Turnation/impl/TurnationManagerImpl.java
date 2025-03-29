package it.unibo.monopoly.model.turnation.impl;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public class TurnationManagerImpl implements TurnationManager {
    @Override
    public boolean isOver() { return false; }
    @Override
    public Player getNextPlayer() { return null; }
    @Override
    public Pair<Integer, Integer> moveByDices() { return null; }
}
