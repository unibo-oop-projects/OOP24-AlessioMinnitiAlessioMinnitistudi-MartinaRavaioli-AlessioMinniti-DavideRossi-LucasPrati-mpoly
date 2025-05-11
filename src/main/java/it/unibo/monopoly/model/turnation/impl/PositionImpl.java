package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * position implementation.
*/
public class PositionImpl implements Position {
    private int pos;
    public PositionImpl(int value) {
        this.pos=value;
    }

    public int getPos(){
        return this.pos;
    }
 }

