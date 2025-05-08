package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * position implementation.
*/
public class PositionImpl implements Position, Comparable<Position> {
    private Integer pos;

    /**
     * constructor.
     * @param value
    */
    public PositionImpl(final Integer value) {
        this.pos = value;
    }

    /**
     * get the position.
     * @return int
    */
    @Override
    public int getPos() {
        return this.pos;
    }

    /**
     * set the position.
     * @param value
    */
    @Override
    public void setPos(final int value) {
        this.pos = value;
    }

    /**
     * compare to.
     * @param o
     * @return int
    */
    @Override
    public int compareTo(final Position o) {
        return this.pos.compareTo(o.getPos());
    }
}
