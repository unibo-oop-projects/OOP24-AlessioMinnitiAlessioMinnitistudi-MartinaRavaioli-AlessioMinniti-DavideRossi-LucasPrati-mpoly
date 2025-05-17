package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.utils.Identifiable;

/**
 * abstract player id implementation.
*/
public abstract class AbstractIdPlayerImpl implements Identifiable<Integer> {
    private final int id;
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
}
