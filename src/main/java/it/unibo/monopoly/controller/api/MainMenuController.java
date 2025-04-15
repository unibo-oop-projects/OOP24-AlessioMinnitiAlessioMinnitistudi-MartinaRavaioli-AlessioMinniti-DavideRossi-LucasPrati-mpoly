package it.unibo.monopoly.controller.api;


import java.awt.Color;
import java.util.Map;


/**
 * MainMenuLogic interface.
 */
public interface MainMenuController {
    /**
     * @implSpec must create players according the factory template with PlayerFactoryImpl, based on the { @param players } 
     * @param players the list of player data, create players according to this
     */
    void onClickStart(final Map<Color, String> playersSetup);


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
    boolean AlreadyMinPlayers();


    /**
     * @return true if numPlayer reach the maximum, false otherwise
     */
    boolean AlreadyMaxPlayers();

}
