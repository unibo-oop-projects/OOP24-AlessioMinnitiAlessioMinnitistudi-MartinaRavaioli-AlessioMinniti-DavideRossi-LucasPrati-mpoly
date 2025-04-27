package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.resources.Identifiable;

public abstract class TileImpl implements Tile,Identifiable<Position>,Comparable<TileImpl>{
    private Position id; 
    private Type type;

    protected TileImpl(Position pos, Type type){
        this.id=pos;
        this.type=type;
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
        return Integer.compare(this.getID().getPos(),o.getID().getPos());
    }
    
    @Override
    public Type getType(){
        return this.type;
    }

    @Override
    public void setType(Type type){
        this.type=type;
    }
}
