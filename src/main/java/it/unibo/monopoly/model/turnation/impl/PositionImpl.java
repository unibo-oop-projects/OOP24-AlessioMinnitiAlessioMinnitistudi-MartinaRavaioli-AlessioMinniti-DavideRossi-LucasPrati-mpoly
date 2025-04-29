package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * position implementation.
*/
public class PositionImpl implements Position,Comparable<Position> {
    private Integer pos;

    public PositionImpl(Integer value){
        this.pos=value;
    }

    @Override
    public int getPos() {
        return this.pos;
    }

    @Override
    public void setPos(int value) {
        this.pos=value;
    }

    @Override
    public int compareTo(Position o) {
        return this.pos.compareTo(o.getPos());
    } 
    
}
