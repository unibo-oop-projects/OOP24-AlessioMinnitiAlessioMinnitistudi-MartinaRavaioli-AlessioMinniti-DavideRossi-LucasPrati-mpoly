package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.resources.Identifiable;
/**
* tile implementation.
*/
public abstract class TileImpl implements Tile, Identifiable<String>, Comparable<TileImpl> {
    private String name;
    private final Position pos; 
    private Group group;
    /**
    * constructor.
    * @param name
    * @param pos
    * @param group
    */
    protected TileImpl(final String name, final Position pos, final Group group) {
        this.name = name;
        this.pos = pos;
        this.group = group;
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
    public String getID() {
        return this.getName();
    }
    /**
    * set ID.
    * @param value
    */
    @Override
    public void setID(final String value) {
        this.name = value;
    }
    /**
    * compare to.
    * @param o
    * @return int
    */
    @Override
    public int compareTo(final TileImpl o) {
        return Integer.compare(this.getPosition().getPos(), o.getPosition().getPos());
    }
    /**
    * get the Group.
    * @return Group
    */
    @Override
    public Group getGroup() {
        return this.group;
    }
    /**
    * set the Group.
    * @param group
    */
    @Override
    public void setGroup(final Group group) {
        this.group = group;
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
        result = prime * result + ((group == null) ? 0 : group.hashCode());
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
        return this.group == other.group;
    }
}
