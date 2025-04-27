package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * special tile implementation.
*/
public class SpecialImpl extends TileImpl implements Special {

    public SpecialImpl(Position pos, Type type) {
        super(pos, type);
    }
    @Override
    public final Effect getEffect() { 
        return null; 
    }
}
