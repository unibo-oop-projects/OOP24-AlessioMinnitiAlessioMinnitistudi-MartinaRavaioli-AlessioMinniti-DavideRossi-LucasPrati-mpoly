package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

/**
* property interface.
*/
public interface Property extends Tile {
    /**
     * set the owner.
     * @param owner
    */
    void setOwner(Player owner);

    /**
     * get the owner.
     * @return Player
    */
    Player getOwner();

    /**
     * get the owner.
     * @return int
    */
    int getRent();
}
