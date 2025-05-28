package it.unibo.monopoly.model.turnation.impl;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;

import it.unibo.monopoly.model.turnation.api.Position;


/**
 * {@link Position}'s implementation.
*/
public class PositionImpl implements Position, Comparable<Position>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Integer MAX_POS = 40;
    private Integer value;

    /**
     * constructor.
     * @param value
    */
    @JsonCreator
    public PositionImpl(final Integer value) {
        this.value = value;
    }

    /**
     * get the position.
     * @return int
    */
    @Override
    public int getPos() {
        return this.value;
    }

    /**
     * set the position.
     * @param value
    */
    @Override
    public void setPos(final int value) {
        if (value < MAX_POS) {
            this.value = value;
        } else {
            final int tempVal = value - MAX_POS;
            this.value = tempVal;
        }
    }

    /**
     * compare to.
     * @param o
     * @return int
    */
    @Override
    public int compareTo(final Position o) {
        return this.value.compareTo(o.getPos());
    }
    /**
     * hashcode.
     * @return int
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }
    /**
     * equals.
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
        final PositionImpl other = (PositionImpl) obj;
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

}
