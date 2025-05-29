package it.unibo.monopoly.controller.api;

import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.transactions.api.BankAction;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.view.api.MainGameView;

/**
 * interface for game controller of the game.
 */
public interface GameController {
    /** 
     * End the turn of the user that's currently playing and 
     * start next player's turn.
     * If the player cannot end its turn execution will result
     * in an exception
     */
    void endTurn();

    /**
     * Throw the dices and update the position of the pawn on the gameBoard.
     */
    void throwDices();

    /**
     * Buy the property occupied by the player’s pawn
     * whose turn it is, if the property is not owned 
     * by any other player.
     */
    void buyProperty();

    /**
     * Pay the rent amount to the owner of the property
     * occupied by the player's pawn whose turn it is.
     */
    void payPropertyOwner();

    /**
     * Loads the game rules from the file
     * and asks the {@link MainGameView} to display them.
     */
    void loadRules();

    /**
     * Get the {@link Configuration} for game settings.
     * @return the {@link Configuration} associated to this controller
     */
    Configuration getConfiguration();

    /**
     * Retrieves the player that is 
     * currently playing its turn and
     * asks the {@link MainGameView} to display
     * its information.
     */
    void loadCurrentPlayerInformation();

    /**
     * Allow to attach {@link MainGameView}s to the controller.
     * @param view the view we want to attach to this controller
     */
    void attachView(MainGameView view);

    /**
     * set the game over.
    */
    void gameOver();
    /**
     * remove the player who lose.
    */
    void playerGameOver();
    /**
     * called if a player buy an house.
     * @param prop curr property
    */
    void addHouse(Property prop);
    /**
     * called if a player buy an hotel.
     * @param prop curr property
    */
    void addHotel(Property prop);

    /**
     * get the tiles.
     * @return List Tile
    */
    List<Tile> getTiles();
    /**
     * get the list of the pawns.
     * @return List Pawn
    */
    List<Pawn> getPawns();

    /**
     * get the current player.
     * @return Player
    */
    Player getCurrPlayer();
    /**
     * return the String version of the rent based on the type of property.
     * @param selectedProperty of which you want to get the rent
     * @param collect the property of the player that owns the selected property
     * @return the string
     */
    String getRentString(TitleDeed selectedProperty, Set<TitleDeed> collect);
    /**
     * get the pawn of the current player.
     * @return Pawn
     */
    Pawn getCurrPawn();

    /**
     * Retrieves a {@link BankAction} with the same name ({@link BankAction#getName()})
     * as the one given as input and executes it (calling {@link BankAction#executeTransaction()}).  
     * @param actionName the name of the {@link BankAction} to execute
     */
    void executeAction(String actionName);

}
