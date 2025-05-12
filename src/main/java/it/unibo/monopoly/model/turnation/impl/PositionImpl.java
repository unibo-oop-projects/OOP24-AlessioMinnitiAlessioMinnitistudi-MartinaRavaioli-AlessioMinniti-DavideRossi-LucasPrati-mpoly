package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * position implementation.
*/
public class PositionImpl implements Position {
    private int pos;
    public PositionImpl(int pos2) {
        //TODO Auto-generated constructor stub
    }
    /**
     * get pos.
     * @return int
    */
    @Override
    public int getPos() {
        return this.pos;
    }
    /**
     * set pos.
     * @param value
    */
    @Override
    public void setPos(final int value) {
        this.pos = value;
    } 
}
