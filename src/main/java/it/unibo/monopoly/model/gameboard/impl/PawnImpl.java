package it.unibo.monopoly.model.gameboard.impl;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.utils.api.Identifiable;

/**
 * {@link Pawn} implementation.
*/
public class PawnImpl implements Pawn, Identifiable<Integer> {
    private final int id; /**id. */
    private Position pos; /**position. */
    private Position prevPos; /**position. */
    private Color color; /**color. */
    private String shape; /**shape. */
    /**
     * constructor.
     * @param id id
     * @param pos initial position
     * @param color color
     * @param shape shape
    */
    public PawnImpl(final int id, final Position pos, final Color color, final String shape) {
        this.id = id;
        this.pos = new PositionImpl(pos.getPos());
        setColor(color);
        setShape(shape);
    }
    /**
     * constructor.
     * @param id id
     * @param pos initial position
     * @param color color
    */
    public PawnImpl(final int id, final Position pos, final Color color) {
        this.id = id;
        this.pos = new PositionImpl(pos.getPos());
        setColor(color);
        setShape("square");
    }

    @Override
    public final void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public final void setShape(final String shape) {
        if (shape.isEmpty() || shape.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.shape = shape;
    }

    @Override
    public final Color getColor() {
        return this.color;
    }

    @Override
    public final String getShape() {
        return this.shape;
    }

    @Override
    public final Position getPosition() {
        return new PositionImpl(this.pos.getPos());
    }

    @Override
    public final Position getPreviousPosition() {
        return new PositionImpl(this.prevPos.getPos());
    }

    @Override
    public final void setPosition(final Position pos) {
        this.prevPos = new PositionImpl(this.pos.getPos());
        this.pos = new PositionImpl(pos.getPos());
    }

    @Override
    public final void move(final int steps) {
        this.prevPos = new PositionImpl(this.pos.getPos());
        this.pos.setPos(this.pos.getPos() + steps);
    }
    @Override
    public final Integer getID() {
        return this.id;
    }
}
