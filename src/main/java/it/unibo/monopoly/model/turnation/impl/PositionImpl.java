package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * position implementation.
*/
public class PositionImpl implements Position {
    private int pos;

    @Override
    public int getPos() {
        return this.pos;
    }

    @Override
    public void setPos(int value) {
        this.pos=value;
    } 
}