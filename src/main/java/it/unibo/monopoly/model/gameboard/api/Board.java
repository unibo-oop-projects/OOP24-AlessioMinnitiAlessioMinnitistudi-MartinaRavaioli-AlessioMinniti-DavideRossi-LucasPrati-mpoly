package it.unibo.monopoly.model.gameboard.api;

import java.util.Collection;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * board interface.
 */
public interface Board {

    //call to return a tile
    /**
     * get the tile.
     * @param pos
     * @return return a tile
     */
    Tile getTile(Position pos);

    /**
     * get the tile of the pawn.
     * @param p
     * @return Tile
     */
    Tile getTileForPawn(Pawn p);

    /**
     * move the pawn.
     * @param player
     * @param value
    */
    void movePawn(Pawn player, Collection<Integer> value);
    /**
     * get the pawn with the given id.
     * @param id
     * @return Pawn
    */
    Pawn getPawn(int id);
}
