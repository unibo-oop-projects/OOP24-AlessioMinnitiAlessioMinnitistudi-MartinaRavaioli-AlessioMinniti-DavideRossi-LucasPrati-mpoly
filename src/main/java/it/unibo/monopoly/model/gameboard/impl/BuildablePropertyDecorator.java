package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.BuildableProperty;
/**
    * buildable property decorator.
*/
public class BuildablePropertyDecorator extends NormalPropertyDecorator implements BuildableProperty {
    /**
     * constructor.
     * @param prop
    */
    public BuildablePropertyDecorator(final BuildableProperty prop) {
        super(prop);
    }

    @Override
    public final void buildHouse() {
        ((BuildableProperty) this.getProperty()).buildHouse();
    }

    @Override
    public final void buildHotel() {
        ((BuildableProperty) this.getProperty()).buildHotel();
    }

    @Override
    public final int getNHouses() {
        return ((BuildableProperty) this.getProperty()).getNHouses();
    }

    @Override
    public final boolean hasHotel() {
        return ((BuildableProperty) this.getProperty()).hasHotel();
    }

    @Override
    public final boolean canBuildHouse() {
        return ((BuildableProperty) this.getProperty()).canBuildHouse();
    }

    @Override
    public final boolean canBuildHotel() {
        return ((BuildableProperty) this.getProperty()).canBuildHotel();
    }

}
