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
    public final boolean isBuildable() {
        return false;
    }

    @Override
    public final void buildHouse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildHouse'");
    }

    @Override
    public final void buildHotel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildHotel'");
    }

    @Override
    public final int getNHouses() {
        return 0;
    }

    @Override
    public final boolean hasHotel() {
        return false;
    }

    @Override
    public final boolean canBuildHouse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBuildHouse'");
    }

    @Override
    public final boolean canBuildHotel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBuildHotel'");
    }

    @Override
    public final void deleteHouse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteHouse'");
    }

    @Override
    public final void deleteHotel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteHotel'");
    }

    @Override
    public boolean canDeleteHouse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canDeleteHouse'");
    }
}
