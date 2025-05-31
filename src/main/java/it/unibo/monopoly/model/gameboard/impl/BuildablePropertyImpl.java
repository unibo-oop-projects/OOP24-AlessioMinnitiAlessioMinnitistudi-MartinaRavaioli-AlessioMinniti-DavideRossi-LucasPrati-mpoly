package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.BuildableProperty;
import it.unibo.monopoly.model.turnation.api.Position;
/**
    * buildable property implementation.
*/
public class BuildablePropertyImpl extends TileImpl implements BuildableProperty {
    private static final int MAX_HOUSES = 4; /**max number of houses. */
    private int nHouses; /**number of hotel. */
    private boolean hotel; /**tells if it has an hotel. */

    /**
     * constructor.
     * @param name property's name
     * @param position position of the property
     * @param group property group
    */
    public BuildablePropertyImpl(final String name, final Position position, final Group group) { 
        super(name, position, group);
        this.nHouses = 0;
        this.hotel = false;
    }

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
    public final boolean isBuildable() {
        return true;
    }

    @Override
    public final boolean canBuildHouse() {
        return getNHouses() <= MAX_HOUSES;
    }

    @Override
    public final boolean canBuildHotel() {
        return !hasHotel();
    }
}
