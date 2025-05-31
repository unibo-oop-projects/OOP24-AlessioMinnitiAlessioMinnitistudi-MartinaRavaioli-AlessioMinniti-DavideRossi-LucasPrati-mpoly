package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * {@link Property} implementation.
*/
public class NormalPropertyImpl extends TileImpl implements Property {

    /**
     * constructor.
     * @param name name
     * @param position position
     * @param group group
    */
    public NormalPropertyImpl(final String name, final Position position, final Group group) { 
        super(name, position, group);
    }

    @Override
    public boolean isBuildable() {
        return false;
    }
}
