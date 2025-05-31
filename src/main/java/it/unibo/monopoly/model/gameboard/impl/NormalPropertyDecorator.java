package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

public class NormalPropertyDecorator implements Property {
    protected final Property decorated;

    public NormalPropertyDecorator(final Property prop) {
        this.decorated = prop;
    }

    @Override
    public Group getGroup() {
        return this.decorated.getGroup();
    }

    @Override
    public void setGroup(Group group) {
        this.decorated.setGroup(group);
    }

    @Override
    public Position getPosition() {
        return this.decorated.getPosition();
    }

    @Override
    public String getName() {
        return this.decorated.getName();
    }

    @Override
    public boolean isBuildable() {
        return this.decorated.isBuildable();
    }

}
