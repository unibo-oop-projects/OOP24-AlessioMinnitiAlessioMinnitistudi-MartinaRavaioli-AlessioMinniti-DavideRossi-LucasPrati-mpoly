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
        //this.nHouses = 0;
        //this.hotel = false;
    }

    @Override
    public final boolean canBuildHouse() {
        return false;
        //return getNHouses() <= MAX_HOUSES;
    }

    @Override
    public final boolean canBuildHotel() {
        return false;
        //return !hasHotel();
    }

    @Override
    public boolean isBuildable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBuildable'");
    }
}
