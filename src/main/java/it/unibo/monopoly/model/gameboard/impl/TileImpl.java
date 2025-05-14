package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.utils.Identifiable;


/**
* {@link Tile} implementation.
*/
public abstract class TileImpl implements Tile, Identifiable<Position>, Comparable<TileImpl> {
    private final String name;
    private final Position pos; 
    private Type type;

    /**
    * constructor.
    * @param name
    * @param pos
    * @param type
    */
    protected TileImpl(final String name, final Position pos, final Type type) {
        this.name = name;
        this.pos = pos;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getID() {
        return new PositionImpl(this.pos.getPos());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final TileImpl o) {
        return Integer.compare(this.getID().getPos(), o.getID().getPos());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(final Type type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return new PositionImpl(pos.getPos());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pos == null) ? 0 : pos.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TileImpl other = (TileImpl) obj;
        if (this.pos == null) {
            if (other.pos != null) {
                return false;
            }
        } else if (!pos.equals(other.pos)) {
            return false;
        }
        return this.type == other.type;
    }
}
