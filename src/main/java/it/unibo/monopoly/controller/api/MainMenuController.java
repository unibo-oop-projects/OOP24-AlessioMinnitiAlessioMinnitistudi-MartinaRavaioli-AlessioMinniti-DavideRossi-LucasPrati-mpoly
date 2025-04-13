package it.unibo.monopoly.controller.api;


import java.util.List;
import it.unibo.monopoly.utils.PlayerSetup;

/**
 * MainMenuLogic interface.
 */
public interface MainMenuController {
    /**
     * @implSpec must create players according the factory template with PlayerFactoryImpl, based on the { @param players } 
     * @param players the list of player data, create players according to this
     */
    void onClickStart(List<PlayerSetup> players);

    /**
     * @implSpec must create a new RulesWindow to display the game rules 
     */
    void onClickShowRules();
}
