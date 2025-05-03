package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * Interface for the game view.
 * An interface containing all methods necessary to reflect the state of the game
 */
public interface MainGameView {

    /**
     * Display information related to the player 
     * that is currently playing its turn.
     * @param plData General information of the player 
     * @param accountData the bank account of the player
     */
    void displayCurrentPlayerInfo(Player plData, BankAccount accountData);

    /**
     * Display information of the {@link TitleDeed}
     * corresponding to the {@link Property} the player's {@link Pawn}
     * is currently on
     * @param propertyContract the title deed to display
     */
    void displayPropertyContract(TitleDeed propertyContract);
}
