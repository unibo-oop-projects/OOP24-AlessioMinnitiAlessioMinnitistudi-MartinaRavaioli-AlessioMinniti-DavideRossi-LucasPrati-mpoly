package it.unibo.monopoly.model.turnation.impl;

import java.util.Collection;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * turnation manager implementation.
*/
public class TurnationManagerImpl implements TurnationManager {
    private Player currPlayer;
    @Override
    public final boolean isOver() { 
        return false; 
    }
    @Override
    public final Player getNextPlayer() { 
        return null; 
    }
    @Override
    public final Collection<Integer> moveByDices() { 
        return null; 
    }
    @Override
    public void setOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setOver'");
    }
    public final int getIdCurrPlayer() {
        return this.currPlayer.getID();
    }
}
