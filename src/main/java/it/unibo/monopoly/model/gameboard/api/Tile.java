package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.gameboard.impl.Type;

/**
 * tile interface.
*/
public interface Tile {
    /**
    * get the type.
    * @return Type
    */
    Type getType();
    /**
    * set the type.
    * @param type
    */
    void setType(Type type);
}
