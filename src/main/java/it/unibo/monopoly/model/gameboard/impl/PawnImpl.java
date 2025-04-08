package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

public class PawnImpl extends PlayerImpl implements Pawn {
    
    public PawnImpl(int id) {
        super(id);
    }

    private PositionImpl pos;

    @Override
    public Position getPosition() {
        return this.pos;
    }

    @Override
    public void move(int steps) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
    
}
