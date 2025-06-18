package it.unibo.monopoly.model.gameboard.api;

import java.util.Collection;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.ChancheAndCommunityChestDeck;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ChanceAndCommunityChestCard;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * board interface.
 */
public interface Board {

    //call to return a tile
    /**
     * get the tile.
     * @param pos position
     * @return return a tile
     */
    Tile getTile(Position pos);

    /**
     * get the tile of the pawn.
     * @param id id of the pawn given
     * @return Tile
     */
    Tile getTileForPawn(int id);

    /**
     * move the pawn.
     * @param id id of the pawn who has to move 
     * @param value value
     * @return the difference between the old and the new position
    */
    int movePawn(int id, Collection<Integer> value);

    /**
     * get the pawn with the given id.
     * @param id id
     * @return Pawn
    */
    Pawn getPawn(int id);

    /**
     * remove a pawn.
     * @param id id of the pawn to remove
    */
    void removePawn(int id);

    /**
     * add a pawn.
     * @param p pawn to add
    */
    void addPawn(Pawn p);

    /**
     * get all the pawns in a tile.
     * @param tile tile given
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
     * get a list with all the pawns.
     * @return List of Pawns
    */
    List<Pawn> getPawns();

    /**
     * move a pawn in a selected tile.
     * @param id id of the pawn to move
     * @param name name of the tile in which the pawn must move
    */
    void movePawnInTile(int id, String name);

    /**
     * get a tile searched by the name given.
     * @param name name of the tile to find
     * @return Tile
    */
    Tile getTile(String name);

    /**
     * add a tile.
     * @param tile tile to add
    */
    void addTile(Tile tile);
    /**
     * check if an house can be built in the property.
     * @param prop house's property
     * @return bool
    */
    boolean canBuildHouseInProperty(Property prop);
    /**
     * check if an hotel can be built in the property.
     * @param prop hotel's property
     * @return bool
    */
    boolean canBuildHotelInProperty(Property prop);
    /**
     * build house in the property.
     * @param name name of the property to change
     * @return int
    */
    int buildHouseInProperty(String name);
    /**
     * build hotel in the property.
     * @param name property to change
     * @return bool
    */
    boolean buildHotelInProperty(String name);
    /**
     * delete an house in the property.
     * @param name name of the property
     * @return bool
     */
    int deleteHouseInProperty(String name) throws IllegalAccessException;
    /**
     * delete the hotel in the property.
     * @param name name of the property
     * @return bool
     */
    boolean deleteHotelInProperty(String name) throws IllegalAccessException;
    /**
     * get the previous position of the pawn associated with the id. 
     * @param id id of the pawn
     * @return Position
     */
    Position getPrevPawnPosition(int id);
    /**
     * draw a card by calling the method of the deck.
     * @return Chance or Community card
     */
    ChanceAndCommunityChestCard draw();

    /**
     * add the chance and community chest deck to the board.
     * @param deck the deck
     */
    void addDeck(ChancheAndCommunityChestDeck deck);
}
