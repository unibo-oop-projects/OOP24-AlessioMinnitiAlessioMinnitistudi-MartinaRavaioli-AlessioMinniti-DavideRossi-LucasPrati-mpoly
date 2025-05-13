package it.unibo.monopoly.model.gameboard.impl;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.PawnFactory;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.AbstractIdPlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

/**
 * pawn implementation.
*/
public class PawnImpl extends AbstractIdPlayerImpl implements Pawn, PawnFactory {
    private Position pos;
    private Color color;
    private String shape;
    /**
     * constructor.
     * @param id
     * @param pos
     * @param color
     * @param shape
    */
    public PawnImpl(final int id, final Position pos, final Color color, final String shape) {
        super(id);
        this.pos = new PositionImpl(pos.getPos());
        setColor(color);
        setShape(shape);
    }
    /**
     * constructor.
     * @param id
     * @param pos
     * @param color
    */
    public PawnImpl(final int id, final Position pos, final Color color) {
        super(id);
        this.pos = new PositionImpl(pos.getPos());
        setColor(color);
        setShape("square");
    }
    /**
     * set color.
     * @param color
    */
    public final void setColor(final Color color) {
        this.color = color;
    }

    /**
     * set the shape.
     * @param shape
    */
    public final void setShape(final String shape) {
        if (shape.isEmpty() || shape.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.shape = shape;
    }

    /**
     * get the color.
     * @return Color
    */
    @Override
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
        return new PositionImpl(this.pos.getPos());
    }

    /**
     * set the position.
     * @param pos
    */
    public void setPosition(final Position pos) {
        this.pos = new PositionImpl(pos.getPos());
    }

    /**
     * move a pawn.
     * @param steps
    */
    @Override
    public void move(final int steps) {
        this.pos.setPos(this.pos.getPos() + steps);
    }
    /**
     * factory method to create basic pawns.
     * @param id
     * @param pos
     * @param color
     * @return Pawn
    */
    @Override
    public Pawn createBasic(final int id, final Position pos, final Color color) {
        return new PawnImpl(id, pos, color);
    }
    /**
     * factory method to create advanced pawns.
     * @param id
     * @param pos
     * @param color
     * @param shape
     * @return Pawn
    */
    @Override
    public Pawn createAdvanced(final int id, final Position pos, final Color color, final String shape) {
        return  new PawnImpl(id, pos, color, shape);
    }
}
