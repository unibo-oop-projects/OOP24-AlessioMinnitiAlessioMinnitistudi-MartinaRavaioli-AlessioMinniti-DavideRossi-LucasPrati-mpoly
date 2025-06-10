package it.unibo.monopoly.view.api;

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
    void displayPlayersMenu();
    
    /**
     * Displays the game rules and general information to play the game.
     * @param rules the text of the game rules
     */
    void displayRules(String rules);

    /**
     * Displays an error message right before close the UI.
     * @param message the message to display
     */
    void displayErrorAndExit(String message);
    
    /**
     * Ask the {@code view} to update the current game mode set.
     */
    void refreshSettingsData();
    
    /**
     * Ask the {@code view} to update the current number of players set.
     */
    void refreshNumPlayers();

}
