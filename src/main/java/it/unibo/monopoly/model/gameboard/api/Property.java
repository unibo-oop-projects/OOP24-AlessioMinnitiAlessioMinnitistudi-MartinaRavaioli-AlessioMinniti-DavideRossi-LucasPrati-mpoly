package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * property interface.
*/
public interface Property extends Tile {
    //set the owner
    /**
     * @param owner
     */
    void setOwner(Player owner);
    //return the owner
    /**
     * @return owner
     */
    Player getOwner();
    //get the price
    /**
     * @return price
     */
    int getRent();
    Object getName();
}
