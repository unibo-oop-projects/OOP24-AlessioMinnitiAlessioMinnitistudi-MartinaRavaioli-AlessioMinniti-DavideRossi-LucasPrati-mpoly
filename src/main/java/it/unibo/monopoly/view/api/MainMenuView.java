package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.view.impl.MainMenuViewImpl;

/**
 * {@link MainMenuViewImpl}'s interface.
 */
public interface MainMenuView {

    /**
     * Ask the {@code view} to display the main menu of the game.
     */
    void displayMainMenu();

    /**
     * Displays the settings' menu for allow to select the game mode.
     */
    void displaySettingsMenu();

    /**
     * Displays the starter menu for allow to define the players' information.
     */
    void displaySetupMenu();

    /**
     * Displays the game rules and general information to play the game.
     * @param rules the text of the game rules
     */
    void displayRules(String rules);

    /**
     * Displays an error message right before close the UI.
     * @param title the title to display
     * @param message the message to display
     */
    void displayErrorAndExit(String title, String message);

    /**
     * Displays a message.
     * @param title the title to display
     * @param message the message to display
     */
    void showInfoMessage(String title, String message);

    /**
     * Ask the {@code view} to display the current game mode set.
     * @param type the {@link BankAccountType} selected
     */
    void refreshSettingsData(BankAccountType type);

    /**
     * Ask the {@code view} to update the current number of players set.
     * @param num the number of player to display
     * @param reachMinPlayers whether the number of players is equal to the minimum allowed
     * @param reachMaxPlayers whether the number of players is equal to the maximum allowed
     */
    void refreshNumPlayers(int num, boolean reachMinPlayers, boolean reachMaxPlayers);

    /**
     * Close the main menu, the game begin.
     */
    void disposeMainMenu();

}
