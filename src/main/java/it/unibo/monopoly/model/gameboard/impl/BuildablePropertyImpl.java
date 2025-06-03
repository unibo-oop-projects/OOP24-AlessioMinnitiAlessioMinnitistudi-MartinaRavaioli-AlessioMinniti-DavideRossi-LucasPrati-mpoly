package it.unibo.monopoly.model.gameboard.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;
/**
    * buildable property implementation.
*/
public class BuildablePropertyImpl implements Property {
    private static final int MAX_HOUSES = 4; /**max number of houses. */
    private int nHouses; /**number of hotel. */
    private boolean hotel; /**tells if it has an hotel. */
    private final Property decorated; /**property to decorate */

    /**
     * constructor.
     * @param prop property to decorate
    */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "Injection of shared mutable dependencies is intentional and controlled in this architecture."
    )
    public BuildablePropertyImpl(final Property prop) {
        this.decorated = prop;
        this.nHouses = 0;
        this.hotel = false;
    }

    @Override
    public final int getNHouses() {
        return this.nHouses;
    }

    @Override
    public final void buildHouse() {
        if (canBuildHouse()) {
            this.nHouses++;
        } else {
            throw new IllegalArgumentException("max num houses reached");
        }
    }

    @Override
    public final void buildHotel() {
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
        return !hasHotel() && getNHouses() == MAX_HOUSES;
    }

    @Override
    public final Group getGroup() {
        return this.decorated.getGroup();
    }

    @Override
    public final void setGroup(final Group group) {
        this.decorated.setGroup(group);
    }

    @Override
    public final Position getPosition() {
        return this.decorated.getPosition();
    }

    @Override
    public final String getName() {
        return this.decorated.getName();
    }

    @Override
    public final void deleteHouse() throws IllegalAccessException {
        if (getNHouses() < 1) {
            throw new IllegalAccessException("the property doesn't have any houses");
        }

        this.nHouses--;
    }

    @Override
    public final void deleteHotel() throws IllegalAccessException {
        if (!hasHotel()) {
            throw new IllegalAccessException("the property doesn't have hotel");
        }

        this.hotel = false;
    }
}
