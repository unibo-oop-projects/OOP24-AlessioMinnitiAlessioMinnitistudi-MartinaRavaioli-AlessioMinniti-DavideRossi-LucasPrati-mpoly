package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * player implementation.
*/
public class PlayerImpl implements Player {
    @Override
    public final Position getPosition() { 
        return null; 
    }
    @Override
    public void makeMove(final int steps) { }
    @Override
    public final boolean isAlive() { 
        return false; 
    }
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getID'");
    }
}
