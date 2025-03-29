package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

//interface for the properties 
public interface Property extends Tile {
    //set the owner
    /**
     * @param owner
     */
    void setOwner(Player owner);
    //return the owner
    /**
     * @return
     */
    Player getOwner();
    //get the price
    /**
     * @return
     */
    int getRent();
}
