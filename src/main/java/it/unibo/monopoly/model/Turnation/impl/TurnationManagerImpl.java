package it.unibo.monopoly.model.turnation.impl;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * turnation manager implementation.
*/
public class TurnationManagerImpl implements TurnationManager {
    @Override
    public final boolean isOver() { 
        return false; 
    }
    @Override
    public final Player getNextPlayer() { 
        return null; 
    }
    @Override
    public final Pair<Integer, Integer> moveByDices() { 
        return null; 
    }
}
