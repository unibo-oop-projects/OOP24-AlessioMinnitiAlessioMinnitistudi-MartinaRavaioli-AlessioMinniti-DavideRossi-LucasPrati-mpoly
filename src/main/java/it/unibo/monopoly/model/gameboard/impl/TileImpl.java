package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.resources.Identifiable;

public abstract class TileImpl implements Tile,Identifiable<Position>{
    private Position id; 

    protected TileImpl(Position pos){
        this.id=pos;
    }
    @Override
    public Position getID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getID'");
    }

    @Override
    public void setID(Position value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setID'");
    }
    
}
