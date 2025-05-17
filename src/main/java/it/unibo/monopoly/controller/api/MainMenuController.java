package it.unibo.monopoly.controller.api;

import java.awt.Color;
import java.io.IOException;
import java.util.Map;

import it.unibo.monopoly.controller.MainMenuControllerImpl;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.utils.ResourceLoader;


/**
 * {@link MainMenuControllerImpl} interface.
 */
public interface MainMenuController {

    /**
     * Must initialize all the game, starting from the given {@code playerSetup}.
     * @param playersSetup the list of player data, create players according to this
     * @throws IOException if the loading from {@code JSON}s failed
     * @throws NullPointerException if {@code id}, {@code name} or {@code color} are {@code null}
     */
    void onClickStart(Map<Color, String> playersSetup) throws IOException;

    /**
     * Decrease the selected number of players.
     */
    void decreaseNumPlayer();

    /**
     * Increase the selected number of players.
     */
    void increaseNumPlayer();

    /**
     * @return the the selected number of players
     */
    int getNumPlayers();

    /**
     * @return true if the selected number of players reach the minimum, false otherwise
     */
    boolean alreadyMinPlayers();

    /**
     * @return true if the selected number of players reach the maximum, false otherwise
     */
    boolean alreadyMaxPlayers();

    /**
     * @return the type of bank account choosen for the game
     */
    BankAccountType getBankAccountType();

    /**
     * @param bankAccountType the {@link BankAccountType} to set
     */
    void setBankAccountType(BankAccountType bankAccountType);

    /**
     * Use a {@link ResourceLoader} for getting a {@link String} with all the rules of the game.
     * 
     * @return a {@link String} with all the rules of the game
     */
    String getRules();

}
