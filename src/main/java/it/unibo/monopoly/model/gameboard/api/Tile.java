package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.gameboard.impl.Type;
import it.unibo.monopoly.model.turnation.api.Position;

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
    /**
    * get the position.
    * @return position
    */
    Position getPosition();
    /**
    * get the name.
    * @return String
    */
    String getName();

}
