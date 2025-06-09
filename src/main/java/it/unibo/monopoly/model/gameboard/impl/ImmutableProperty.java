package it.unibo.monopoly.model.gameboard.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;
/**
 * immutable property used to give the info of the house and the hotel in the titledeed.
 * it contains a property object.
 * only the methods that give the houses and hotel info will be called, the others will throw exceptions.
*/
public final class ImmutableProperty implements Property {
    private final BuildablePropertyImpl property;
    /**
    * constructor.
    * it has to take the reference of the property, not a copy
    * @param prop property
    */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "Injection of shared mutable dependencies is intentional and controlled in this architecture."
    )
    public ImmutableProperty(final BuildablePropertyImpl prop) {
        this.property = prop;
    }

    @Override
    public Group getGroup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGroup'");
    }

    @Override
    public void setGroup(final Group group) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGroup'");
    }

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public String getName() {
        return this.property.getName();
    }

    @Override
    public void buildHouse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildHouse'");
    }

    @Override
    public void buildHotel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildHotel'");
    }

    @Override
    public int getNHouses() {
        return this.property.getNHouses();
    }

    @Override
    public boolean hasHotel() {
        return this.property.hasHotel();
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

    @Override
    public boolean isBuildable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBuildable'");
    }

    @Override
    public void deleteHouse() throws IllegalAccessException {
        // TODO Auto-generated method stub
        throw new IllegalAccessException("operation not permitted");
    }

    @Override
    public void deleteHotel() throws IllegalAccessException {
        // TODO Auto-generated method stub
        throw new IllegalAccessException("operation not permitted");
    }

}
