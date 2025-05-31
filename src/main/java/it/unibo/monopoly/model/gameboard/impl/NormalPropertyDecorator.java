package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;
/**
    * decorator for normal property.
*/
public class NormalPropertyDecorator implements Property {
    private final Property decorated; /** property to decorate.*/

    /**
        * constructor.
        * @param prop property to decorate
    */
    public NormalPropertyDecorator(final Property prop) {
        this.decorated = prop;
    }
    /**
        * get the property to decorate to the child classes.
        * @return Property to decorate
    */
    protected final Property getProperty() {
        return this.decorated;
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
    public final boolean isBuildable() {
        return this.decorated.isBuildable();
    }

}
