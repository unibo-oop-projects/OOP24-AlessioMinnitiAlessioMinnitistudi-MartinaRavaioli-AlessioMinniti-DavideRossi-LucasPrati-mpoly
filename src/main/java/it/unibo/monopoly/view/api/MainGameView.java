package it.unibo.monopoly.view.api;

import java.util.Set;

import it.unibo.monopoly.controller.api.GameController;
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
     * Display information related to the player 
     * that's next to play its turn.
     * The method also clears all panels that shows informations that are subsequent 
     * to the player's movement.
     * @param plData General information of the player 
     * @param accountData the bank account of the player
     */
    void displayCurrentPlayerInfo(Player plData, BankAccount accountData);

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
}
