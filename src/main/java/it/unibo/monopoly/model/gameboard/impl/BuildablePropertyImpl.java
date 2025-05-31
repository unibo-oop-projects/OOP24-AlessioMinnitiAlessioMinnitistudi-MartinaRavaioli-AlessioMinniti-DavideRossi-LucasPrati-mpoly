package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.BuildableProperty;

public class BuildablePropertyImpl extends TileImpl implements BuildableProperty{
    private static final int MAX_HOUSES = 4; /**max number of houses. */
    private int nHouses; /**number of hotel. */
    private boolean hotel; /**tells if it has an hotel. */
    
    @Override
    public final int getNHouses() {
        return this.nHouses;
    }

    @Override
    public final void buildHouse() {
        if (getGroup() == Group.SOCIETY || getGroup() == Group.STATION) {
            throw new IllegalArgumentException("this type of property can't build an house");
        }
        if (canBuildHouse()) {
            this.nHouses++;
        } else {
            throw new IllegalArgumentException("max num houses reached");
        }
    }

    @Override
    public final void buildHotel() {
        if (getGroup() == Group.SOCIETY || getGroup() == Group.STATION) {
            throw new IllegalArgumentException("this type of property can't build an hotel");
        }
        if (canBuildHotel()) {
            this.hotel = true;
        } else {
            throw new IllegalArgumentException("hotel already exists");
        }
    }

    @Override
    public final boolean hasHotel() {
        return this.hotel;
    }

    @Override
    public boolean isBuildable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBuildable'");
    }

    @Override
    public boolean canBuildHouse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBuildHouse'");
    }

    @Override
    public boolean canBuildHotel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBuildHotel'");
    }
}
