package it.unibo.monopoly.controller.api;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.PropertyAction;
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
     * get the pawn of the current player.
     * @return Pawn
     */
    Pawn getCurrPawn();

    /**
     * Retrieves a {@link PropertyAction} with the same name ({@link PropertyAction#getName()})
     * as the one given as input and executes it (calling {@link PropertyAction#executePropertyAction(Board, Bank)}).
     * @param actionName the name of the {@link PropertyAction} to execute
     */
    void executeAction(String actionName);
    /**
     * start the UI, initializing the game view and the bank state.
     */
    void start();
    /**
     * get the final ranking of the players.
     * @return List of all players with their ranking
    */
    List<Pair<String, Integer>> getRanking();
    /**
     * get the winner.
     * @return winner data
    */
    Pair<String, Integer> getWinner();
    /**
     * end the turn even if the player dies.
    */
    void endTurnPlayerDies();
    /**
     * refresh the properties of the players.
     */
    void refreshBankPlayerInfo();
}
