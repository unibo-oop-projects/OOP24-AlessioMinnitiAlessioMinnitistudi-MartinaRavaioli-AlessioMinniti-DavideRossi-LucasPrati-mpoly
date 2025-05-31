package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.BuildableProperty;

public class BuildablePropertyDecorator extends NormalPropertyDecorator implements BuildableProperty {

    public BuildablePropertyDecorator(BuildableProperty prop) {
        super(prop);
    }

    @Override
    public void buildHouse() {
        ((BuildableProperty)this.decorated).buildHouse();
    }

    @Override
    public void buildHotel() {
        ((BuildableProperty)this.decorated).buildHotel();
    }

    @Override
    public int getNHouses() {
        return ((BuildableProperty)this.decorated).getNHouses();
    }

    @Override
    public boolean hasHotel() {
        return ((BuildableProperty)this.decorated).hasHotel();
    }

    @Override
    public boolean canBuildHouse() {
        return ((BuildableProperty)this.decorated).canBuildHouse();
    }

    @Override
    public boolean canBuildHotel() {
        return ((BuildableProperty)this.decorated).canBuildHotel();
    }

}
