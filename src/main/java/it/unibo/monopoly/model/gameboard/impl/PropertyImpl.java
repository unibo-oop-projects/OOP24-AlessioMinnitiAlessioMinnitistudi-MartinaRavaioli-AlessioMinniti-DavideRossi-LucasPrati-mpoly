package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * {@link Property} implementation.
*/
public class PropertyImpl extends TileImpl implements Property {

    private static final int MAX_HOUSES = 4;
    private int nHouses;
    private boolean hotel;

    /**
     * constructor.
     * @param name
     * @param position
     * @param group
    */
    public PropertyImpl(final String name, final Position position, final Group group) { 
        super(name, position, group);
        this.nHouses = 0;
        this.hotel = false;
    }

    /**
     * get the number of houses.
     * @return int
    */
    @Override
    public int getNHouses() {
        return this.nHouses;
    }

    /**
     * add house.
    */
    @Override
    public void buildHouse() {
        if (this.getNHouses() < MAX_HOUSES) {
            this.nHouses++;
        } else {
            throw new IllegalArgumentException("max num houses reached");
        }
    }
    /**
     * add hotel.
    */
    @Override
    public void buildHotel() {
        if (!this.hotel) {
            this.hotel = true;
        } else {
            throw new IllegalArgumentException("hotel already exists");
        }
    }
    /**
     * control if it has an hotel.
     * @return bool
    */
    @Override
    public boolean hasHotel() {
        return this.hotel;
    }
}
