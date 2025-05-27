package it.unibo.monopoly.model.gameboard.api;

import java.util.Collection;
import java.util.List;

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

    /**
     * remove a pawn.
     * @param p
    */
    void removePawn(Pawn p);

    /**
     * add a pawn.
     * @param p
    */
    void addPawn(Pawn p);

    /**
     * get all the pawns in a tile.
     * @param tile
     * @return List of Pawn
    */
    List<Pawn> getPawninTile(Tile tile);

    /**
     * sort the tiles.
    */
    void sortTiles();

    /**
     * get a list with all the tiles.
     * @return List of tiles
    */
    List<Tile> getTiles();

    /**
     * move a pawn in a selected tile.
     * @param pawn
     * @param name
    */
    void movePawnInTile(Pawn pawn, String name);

    /**
     * get a tile searched by the name given.
     * @param name
     * @return Tile
    */
    Tile getTile(String name);
    
    /**
     * add a tile.
     * @param tile
    */
    void addTile(Tile tile);

    /**
     * Set the provided {@link List} and overrides the old one.
     * @param tiles the new {@link List} of {@link Tile} to add to the board
     */
    void setTiles(final List<Tile> tiles);
}
