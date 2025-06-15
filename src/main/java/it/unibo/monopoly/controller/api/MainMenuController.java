package it.unibo.monopoly.controller.api;

import java.awt.Color;
import java.util.Map;

import it.unibo.monopoly.controller.impl.MainMenuControllerImpl;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.utils.impl.Configuration;


/**
 * {@link MainMenuControllerImpl} interface.
 */
public interface MainMenuController {

    /**
     * Must initialize all the game, starting from the given list of players'data.
     * @param playersSetup the players'data, create players according to this
     */
    void onClickStart(Map<Color, String> playersSetup);

    /**
     * Increase the selected number of players.
     */
    void onClickIncrease();

    /**
     * Decrease the selected number of players.
     */
    void onClickDecrease();

    /**
     * Create a UI for display the game's rules.
     */
    void onClickRules();

    /**
     * Show a menu for game mode selection.
     */
    void onClickSettings();

    /**
     * Show a menu for entering player data.
     */
    void onClickContinue();

    /**
     * Change the game mode to Classic.
     */
    void onClickClassicMode();

    /**
     * Change the game mode to Infinity.
     */
    void onClickInfinityMode();

    /**
     * Come back to the main menu.
     */
    void onClickDone();

    /**
     * @return the the selected number of players
     */
    int getNumPlayers();

    /**
     * @return the type of bank account choosen for the game
     */
    BankAccountType getBankAccountType();

    /**
     * @param bankAccountType the {@link BankAccountType} to set
     */
    void setBankAccountType(BankAccountType bankAccountType);

    /**
     * Get the {@link Configuration} for game settings.
     * @return the {@link Configuration} associated to this controller
     */
    Configuration getConfiguration();

    /**
     * Start the UI.
     */
    void start();

    /**
     * Close the UI.
     */
    void disposeMainMenu();

}
