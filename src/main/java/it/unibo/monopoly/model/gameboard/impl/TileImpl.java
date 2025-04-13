package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.resources.Identifiable;

public abstract class TileImpl implements Tile,Identifiable<Position>,Comparable<TileImpl>{
    private Position id; 

    protected TileImpl(Position pos){
        setID(pos);
    }
    @Override
    public Position getID() {
        return this.id;
    }

    @Override
    public void setID(Position value) {
        this.id=value;
    }
    
    @Override
    public int compareTo(TileImpl o){
        return 0;
    }
}
