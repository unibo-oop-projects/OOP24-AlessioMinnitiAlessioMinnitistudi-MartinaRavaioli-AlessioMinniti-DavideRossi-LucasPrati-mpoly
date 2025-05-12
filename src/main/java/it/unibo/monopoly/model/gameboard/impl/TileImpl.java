package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.resources.Identifiable;
/**
* tile implementation.
*/
public abstract class TileImpl implements Tile, Identifiable<Position>, Comparable<TileImpl> {
    private Position id; 
    private Type type;
    /**
    * constructor.
    * @param pos
    * @param type
    */
    protected TileImpl(final Position pos, final Type type) {
        this.id = pos;
        this.type = type;
    }
    /**
    * get ID.
    * @return Position
    */
    @Override
    public Position getID() {
        return this.id;
    }
    /**
    * set ID.
    * @param value
    */
    @Override
    public void setID(final Position value) {
        this.id = value;
    }
    /**
    * compare to.
    * @param o
    * @return int
    */
    @Override
    public int compareTo(final TileImpl o) {
        return Integer.compare(this.getID().getPos(), o.getID().getPos());
    }
    /**
    * get the type.
    * @return Type
    */
    @Override
    public Type getType() {
        return this.type;
    }
    /**
    * set the type.
    * @param type
    */
    @Override
    public void setType(final Type type) {
        this.type = type;
    }
}
