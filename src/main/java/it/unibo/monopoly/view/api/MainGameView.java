package it.unibo.monopoly.view.api;

import java.util.Set;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * Interface for the game view.
 * An object that displays to the user the ongoing game,
 * allowing it to play and iteract with it.
 */
public interface MainGameView {

    /**
     * Ask the {@code view} to refresh the information related
     * to the player that is currently playing. 
     * This method requires the {@link Player} instance and its 
     * associated {@link BankAccount} to be passed as input.
     * @param player an object containing generic information of a player
     * @param account an object containing all information related to the {@code balance} of a specific player
     */
    void refreshCurrentPlayerInfo(Player player, BankAccount account);

    /**
     * Display information of the {@link TitleDeed}
     * corresponding to the {@link Property} the player's {@link Pawn}
     * is currently on.
     * @param propertyContract the title deed to display
     */
    void displayPropertyContract(TitleDeed propertyContract);

    /**
     * Display information of the {@link Special}
     * corresponding to the {@link Tile} the player's {@link Pawn}
     * is currently on.
     * @param tile the tile to display
     */
    void displaySpecialInfo(Special tile);

    /**
     * Display interactable UI elements that show the possible actions for a player.
     * @param actions the set of actions that the player can do. When a player selects an action
     * this will be executed passing to the {@link GameAction} the {@link GameController} instance
     * that was previously attached to this view.
     */
    void showPlayerActions(Set<GameAction> actions);

    /**
     * Displays the game rules and general information
     * to play the game.
     * @param rules the text of the game rules
     */
    void showRules(String rules);


    /**
     * Displays all statistics related to the {@link Player}, its {@link BankAccount}
     * and the {@code titledeeds} owned by that player.
     * @param player The player whose statistics have to be displayed
     * The view will then make subsequent calls to controller methods to retrieve
     * all information related to that player.
     */
    void displayPlayerStats(Player player);

    /**
     * Displays an error on the UI.
     * @param e the {@code exception} thrown and whose information has to be displayed.
     */
    void displayError(Exception e);

    /**
     * Displays a generic message to the user.
     * @param message The message to display
     */
    void displayMessage(String message);
}
