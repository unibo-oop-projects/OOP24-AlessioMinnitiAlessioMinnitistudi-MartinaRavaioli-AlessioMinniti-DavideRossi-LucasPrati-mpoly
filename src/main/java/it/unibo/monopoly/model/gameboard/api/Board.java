package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * board interface.
 */
public interface Board {

    //call to return a tile
    /**
     * @param pos
     * @return return a tile
     */
    Tile getTile(Position pos);
    //call to buy a property
    /**
     * @param prop
     * @param owner
     */
    void buyProperty(Property prop, Player owner);

    Tile getTileForPawn(Pawn p);

    void movePawn(Pawn player);
}
