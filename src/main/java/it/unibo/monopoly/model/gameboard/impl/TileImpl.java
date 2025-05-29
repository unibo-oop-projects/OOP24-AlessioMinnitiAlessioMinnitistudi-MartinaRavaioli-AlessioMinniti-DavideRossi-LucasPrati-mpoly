package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.utils.api.Identifiable;


/**
* {@link Tile} implementation.
*/
public abstract class TileImpl implements Tile, Identifiable<String>, Comparable<TileImpl> {
    private final String name; /**name. */
    private final Position pos; /**position. */
    private Group group; /**group. */

    /**
    * constructor.
    * @param name name
    * @param pos position
    * @param group group
    */
    protected TileImpl(final String name, final Position pos, final Group group) {
        this.name = name;
        this.pos = pos;
        this.group = group;
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
    public String getID() {
        return this.getName();
    }

    /**
    * compare to.
    * @param o tile to compare
    * @return int
    */
    @Override
    public int compareTo(final TileImpl o) {
        return Integer.compare(this.getPosition().getPos(), o.getPosition().getPos());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getGroup() {
        return this.group;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroup(final Group group) {
        this.group = group;
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
        result = prime * result + ((group == null) ? 0 : group.hashCode());
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
        return this.group == other.group;
    }
}
