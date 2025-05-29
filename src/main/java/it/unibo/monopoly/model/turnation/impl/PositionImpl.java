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
     * @param value value
    */
    @JsonCreator
    public PositionImpl(final Integer value) {
        this.value = value;
    }

    @Override
    public final int getPos() {
        return this.value;
    }

    @Override
    public final void setPos(final int value) {
        if (value < MAX_POS) {
            this.value = value;
        } else {
            final int tempVal = value - MAX_POS;
            this.value = tempVal;
        }
    }

    @Override
    public final int compareTo(final Position o) {
        return this.value.compareTo(o.getPos());
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
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
