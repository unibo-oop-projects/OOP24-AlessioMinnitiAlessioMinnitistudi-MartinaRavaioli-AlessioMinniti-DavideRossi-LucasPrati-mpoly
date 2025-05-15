package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * special tile implementation.
*/
public class SpecialImpl extends TileImpl implements Special {
    /**
    * get the Group.
    * @param name
    * @param pos
    * @param group
    */
    public SpecialImpl(final String name, final Position pos, final Group group) {
        super(name, pos, group);
    }
    /**
    * get the Group.
    * @return Effect
    */
    @Override
    public final Effect getEffect() { 
        return null; 
    }

    @Override
    public final Position getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }
}
