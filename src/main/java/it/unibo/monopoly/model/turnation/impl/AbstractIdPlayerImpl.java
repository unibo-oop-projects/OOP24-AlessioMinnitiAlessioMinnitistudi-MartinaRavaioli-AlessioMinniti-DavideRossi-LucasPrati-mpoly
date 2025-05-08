package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.resources.Identifiable;

/**
 * abstract player id implementation.
*/
public abstract class AbstractIdPlayerImpl implements Player, Identifiable<Integer> {
    private int id;
    /**
     * constructor.
     * @param id
    */
    public AbstractIdPlayerImpl(final int id) {
        this.id = id;
    }
    /**
    * get ID.
    * @return Integer
    */
    @Override
    public final Integer getID() {
        return this.id;
    }
    /**
     * set the ID.
     * @param value
    */
    @Override
    public final void setID(final Integer value) {
        this.id = value;
    }
}
