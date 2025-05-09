package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * position implementation.
*/
public class PositionImpl implements Position, Comparable<Position> {
    private final static Integer MAX_POS = 40;
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
        if(value <= MAX_POS){
            this.value = value;
        }
        else{
            throw new IllegalArgumentException("out of bound");
        }
        
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
