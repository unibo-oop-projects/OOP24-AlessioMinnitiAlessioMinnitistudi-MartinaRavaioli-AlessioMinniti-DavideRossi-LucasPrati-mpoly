package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.resources.Identifiable;
/**
* tile implementation.
*/
public abstract class TileImpl implements Tile, Identifiable<Position>, Comparable<TileImpl> {
    private final String name;
    private Position pos; 
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
    * get the name.
    * @return String
    */
    @Override
    public String getName() {
        return this.name;
    }

    /**
    * get ID.
    * @return Position
    */
    @Override
    public Position getID() {
        return new PositionImpl(this.pos.getPos());
    }
    /**
    * set ID.
    * @param value
    */
    @Override
    public void setID(final Position value) {
        this.pos = new PositionImpl(value.getPos());
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
    /**
    * get the position.
    * @return Position
    */
    @Override
    public Position getPosition() {
        return new PositionImpl(pos.getPos());
    }
    /**
    * hash code.
    * @return int
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
    * equals method.
    * @param obj
    * @return bool
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
