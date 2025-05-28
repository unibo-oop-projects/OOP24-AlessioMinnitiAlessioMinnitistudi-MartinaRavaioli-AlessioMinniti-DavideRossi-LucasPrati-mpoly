package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * tile interface.
*/
public interface Tile {

    /**
    * get the Group.
    * @return Group
    */
    Group getGroup();

    /**
    * set the Group.
    * @param group
    */
    void setGroup(Group group);

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
