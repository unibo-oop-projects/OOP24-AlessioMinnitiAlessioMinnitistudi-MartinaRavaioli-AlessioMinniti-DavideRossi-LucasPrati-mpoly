package it.unibo.monopoly.model.gameboard.impl;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.AbstractIdPlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

public class PawnImpl extends AbstractIdPlayerImpl implements Pawn {
    private Position pos;
    private Color color;
    private String shape;
    
    public PawnImpl(Position id,Color color, String shape) {
        super(id.getPos());
        this.pos=new PositionImpl(id.getPos());
        setColor(color);
        setShape(shape);
    }

    public final void setColor(Color color){
        this.color=color;
    }

    public final void setShape(String shape){
        this.shape=shape;
    }

    public Color getColor(){
        return this.color;
    }

    public String getShape(){
        return this.shape;
    }

    @Override
    public Position getPosition() {
        return this.pos;
    }

    @Override
    public void move(int steps) {
        this.pos.setPos(this.pos.getPos()+steps);
    }

 
}
