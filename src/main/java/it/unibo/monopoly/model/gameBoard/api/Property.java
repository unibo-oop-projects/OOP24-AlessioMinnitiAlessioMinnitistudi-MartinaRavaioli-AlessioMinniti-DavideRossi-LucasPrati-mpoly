package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

//interface for the properties 
public interface Property extends Tile {
    //set the owner
    void setOwner(Player owner);
    //return the owner
    Player getOwner();
    //get the price
    int getRent();
}
