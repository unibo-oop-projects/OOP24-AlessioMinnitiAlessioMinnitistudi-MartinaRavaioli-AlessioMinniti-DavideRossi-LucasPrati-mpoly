package it.unibo.monopoly.controller.api;

import java.awt.Color;
import java.io.IOException;
import java.util.Map;

import it.unibo.monopoly.controller.MainMenuControllerImpl;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.utils.ResourceLoader;


/**
 * {@link MainMenuControllerImpl} interface.
 */
public interface MainMenuController {
    /**
     * @implNote must create players according the factory template with PlayerFactoryImpl, based on the { @param players } 
     * @param playersSetup the list of player data, create players according to this
     * @throws IOException if the loading of {@link TitleDeed}s from {@code JSON} failed
     */
    void onClickStart(Map<Color, String> playersSetup) throws IOException;

    /**
     * @implNote decreases the field numPlayer
     */
    void decreaseNumPlayer();

    /**
     * @implNote increases the field numPlayer
     */
    void increaseNumPlayer();

    /**
     * @return the amount of numPlayer
     */
    int getNumPlayers();

    /**
     * @return true if numPlayer reach the minimum, false otherwise
     */
    boolean alreadyMinPlayers();

    /**
     * @return true if numPlayer reach the maximum, false otherwise
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
     * Use a {@link ResourceLoader} for getting the {@link String} with all the rules of the game.
     * 
     * @return a {@link String} with all the rules of the game
     */
    String getRules();

}
