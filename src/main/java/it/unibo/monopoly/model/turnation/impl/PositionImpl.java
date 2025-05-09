package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * position implementation.
*/
public class PositionImpl implements Position, Comparable<Position> {
    private Integer value;

    /**
     * constructor.
     * @param value
    */
    public PositionImpl(final Integer value) {
        this.value = value;
    }

    /**
     * get the position.
     * @return int
    */
    @Override
    public int getPos() {
        return this.value;
    }

    /**
     * set the position.
     * @param value
    */
    @Override
    public void setPos(final int value) {
        this.value = value;
    }

    /**
     * compare to.
     * @param o
     * @return int
    */
    @Override
    public int compareTo(final Position o) {
        return this.value.compareTo(o.getPos());
    }
}
