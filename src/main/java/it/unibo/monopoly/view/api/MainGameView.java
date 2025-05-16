package it.unibo.monopoly.view.api;

import java.util.Set;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * Interface for the game view.
 * An object that displays to the user the ongoing game,
 * allowing it to play and iteract with it.
 */
public interface MainGameView {

    /**
     * Ask the {@code view} to refresh the information related
     * to the player that is currently playing. 
     * Usually this involves a series of calls 
     * to controller methods to retrieve the data and then
     * display it.
     */
    void refreshCurrentPlayerInfo();

    /**
     * Display information of the {@link TitleDeed}
     * corresponding to the {@link Property} the player's {@link Pawn}
     * is currently on.
     * @param propertyContract the title deed to display
     */
    void displayPropertyContract(TitleDeed propertyContract);


    /**
     * Display interactable UI elements that show the possible actions for a player.
     * @param actions the set of actions that the player can do. When a player selects an action
     * this will be executed passing to the {@link GameAction} the {@link GameController} instance
     * that was previously attached to this view.
     */
    void showPlayerActions(Set<GameAction> actions);

    /**
     * Displays an error on the UI.
     * @param e the {@code exception} thrown and whose information has to be displayed.
     */
    void displayError(Exception e);

    /**
     * Displays the game rules and general information
     * to play the game.
     * @param rules the text of the game rules
     */
    void showRules(String rules);
}
