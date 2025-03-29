package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

//Board interface, it's used to manage the gameboard of the game, where will be the tiles and pawns
public interface Board {
    //call to sell an house
    /**
     * @param prop
     */
    void sellHouse(Property prop);
    //call to sell a property
    /**
     * @param prop
     */
    void sellProperty(Property prop);
    //call to return a tile
    /**
     * @param pos
     * @return
     */
    Tile getTile(Position pos);
    //call to buy a property
    /**
     * @param prop
     * @param owner
     */
    void buyProperty(Property prop, Player owner);
}