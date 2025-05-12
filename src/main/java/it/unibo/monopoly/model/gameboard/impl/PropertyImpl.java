package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * property implementation.
*/
public class PropertyImpl extends TileImpl implements Property {
    private static final int MAX_HOUSES = 4;
    private int nHouses;
    private boolean hotel;
    private String name;
    /**
     * constructor.
     * @param name
     * @param id
     * @param type
    */
    public PropertyImpl(final String name, final Position id, final Type type) { 
        super(id, type);
        this.nHouses = 0;
        this.hotel = false;
        setName(name);
    }
    /**
     * set the name.
     * @param name
    */
    public final void setName(final String name) {
        this.name = name;
    }
    /**
     * get the name.
     * @return String
    */
    public String getName() {
        return this.name;
    }

    /**
     * get the number of houses.
     * @return int
    */
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
    public boolean hasHotel() {
        return this.hotel;
    }
}
