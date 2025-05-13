package it.unibo.monopoly.controller.api;


import java.awt.Color;
import java.util.Map;

import it.unibo.monopoly.model.transactions.api.BankAccountType;


/**
 * MainMenuLogic interface.
 */
public interface MainMenuController {
    /**
     * @implSpec must create players according the factory template with PlayerFactoryImpl, based on the { @param players } 
     * @param playersSetup the list of player data, create players according to this
     */
    void onClickStart(Map<Color, String> playersSetup);

    /**
     * @implSpec decreases the field numPlayer
     */
    void decreaseNumPlayer();

    /**
     * @implSpec increases the field numPlayer
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
    

}
