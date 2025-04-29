package it.unibo.monopoly.model.gameboard.impl;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.AbstractIdPlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

public class PawnImpl extends AbstractIdPlayerImpl implements Pawn {
    private Position pos=new PositionImpl();
    private Color color;
    private String shape;
    
    public PawnImpl(Position id,Color color, String shape) {
        super(id.getPos());
        this.pos=id;
    }

    @Override
    public Position getPosition() {
        return this.pos;
    }

    @Override
    public void move(int steps) {
        this.pos.setPos(steps);
    }

 
}
