package it.unibo.monopoly.model.turnation.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

/**
 * {@link PositionImpl} interface.
*/
@JsonDeserialize(as = PositionImpl.class)
public interface Position {
    /**
     * get the position.
     * @return int
    */
    int getPos();

    /**
     * set the position.
     * @param value
    */
    void setPos(int value);
}
