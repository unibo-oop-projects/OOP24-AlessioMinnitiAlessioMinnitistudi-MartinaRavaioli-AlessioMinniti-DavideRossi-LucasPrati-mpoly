package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * {@link Property} implementation.
*/
public class PropertyImpl extends TileImpl implements Property {
    private static final int MAX_HOUSES = 4; /**max number of houses. */
    private int nHouses; /**number of hotel. */
    private boolean hotel; /**tells if it has an hotel. */

    /**
     * constructor.
     * @param name name
     * @param position position
     * @param group group
    */
    public PropertyImpl(final String name, final Position position, final Group group) { 
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
        if (this.getNHouses() < MAX_HOUSES) {
            this.nHouses++;
        } else {
            throw new IllegalArgumentException("max num houses reached");
        }
    }

    @Override
    public final void buildHotel() {
        if (!this.hotel) {
            this.hotel = true;
        } else {
            throw new IllegalArgumentException("hotel already exists");
        }
    }

    @Override
    public final boolean hasHotel() {
        return this.hotel;
    }
}
