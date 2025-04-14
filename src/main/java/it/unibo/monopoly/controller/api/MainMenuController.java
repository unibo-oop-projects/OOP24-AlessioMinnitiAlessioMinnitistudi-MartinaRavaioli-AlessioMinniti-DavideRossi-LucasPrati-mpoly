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
     * 
     */
    void decreaseNumPlayer();


    /**
     * 
     */
    void increaseNumPlayer();


    /**
     * 
     */
    int getNumPlayers();


    /**
     * 
     */
    boolean AlreadyMinPlayers();


    /**
     * 
     */
    boolean AlreadyMaxPlayers();

}
