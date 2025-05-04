package it.unibo.monopoly.view.api;

import java.awt.Component;
import it.unibo.monopoly.controller.api.GameController;

/**
 * Abstract factory to create UI components 
 * necessary to play the game.
 */
public interface GamePanelsFactory {

    /**
     * Returns a panel that shows information of a {@link Player}.
     * @return an object that implements the {@link PlayerPanel} interface
     */
    PlayerPanel userInfoPanel();

    /**
     * Returns a panel that shows information related to a {@link BankAccount}.
     * @return an object that implements the {@link AccountPanel} interface
    */
    AccountPanel bankAccountInfoPanel();

    /**
     * Returns a panel that shows information related to a {@link TitleDeed}.
     * @return an object that implements the {@link ContractPanel} interface
     */
    ContractPanel contractPanel();

    /**
     * Returns a panel that allows the user to do the 
     * basic actions of the game.
     * @param controller the {@link GameController} that is currently handling 
     * the ongoing game
     * @return a generic {@link Component} that embeds UI elements to 
     * perform basic game actions
     */
    Component mainCommandsPanel(GameController controller);
}
