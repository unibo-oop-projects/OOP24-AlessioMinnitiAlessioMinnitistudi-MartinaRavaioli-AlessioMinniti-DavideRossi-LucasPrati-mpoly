package it.unibo.monopoly.model.gameboard.impl;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.AbstractIdPlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

/**
 * pawn implementation.
*/
public class PawnImpl extends AbstractIdPlayerImpl implements Pawn {
    private Position pos;
    private Color color;
    private String shape;
    /**
     * constructor.
     * @param id
     * @param color
     * @param shape
    */
    public PawnImpl(final int id, final Position pos,final Color color, final String shape) {
        super(id);
        this.pos = new PositionImpl(pos.getPos());
        setColor(color);
        setShape(shape);
    }
    /**
     * set color.
     * @param color
    */
    public final void setColor(final Color color) {
        try {
            this.color = color;
        } catch (Exception e) {

        }
        
    }

    /**
     * set the shape.
     * @param shape
    */
    public final void setShape(final String shape) {
        if(shape.isEmpty() || shape.isBlank()){
            throw new IllegalArgumentException();
        }
        this.shape = shape;
    }

    /**
     * get the color.
     * @return Color
    */
    public Color getColor() {
        return this.color;
    }

    /**
     * get the shape.
     * @return String
    */
    public String getShape() {
        return this.shape;
    }

    /**
     * get the position.
     * @return Position
    */
    @Override
    public Position getPosition() {
        return this.pos;
    }

    /**
     * set the position.
     * @param pos
    */
    public void setPosition(final Position pos) {
        this.pos = pos;
    }

    /**
     * move a pawn.
     * @param steps
    */
    @Override
    public void move(final int steps) {
        this.pos.setPos(this.pos.getPos() + steps);
    }

    public static PawnImpl createPawn(final Position pos, final Color color, final String shape) {
        return null;
    }
}
