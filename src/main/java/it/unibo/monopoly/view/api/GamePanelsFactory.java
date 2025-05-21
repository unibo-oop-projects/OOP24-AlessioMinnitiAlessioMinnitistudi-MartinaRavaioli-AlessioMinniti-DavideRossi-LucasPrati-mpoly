package it.unibo.monopoly.view.api;

import java.awt.Component;
import it.unibo.monopoly.controller.api.GameController;

/**
 * Abstract factory to create UI components 
 * necessary to play the game.
 * All these components comply to view specific interfaces
 * that facilitate the panels update by hiding all the UI
 * managements, which is handled by the panels.
 * The specific UI components and libraries used depend 
 * on the factory implementation.
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
     * Returns a {@link GameActionsPanel}, a panel that can display
     * specifc game controls and allow the user to interact with them.
     * @return an object that implements the {@link GameActionsPanel} interface
     */
    GameActionsPanel gameActionsPanel();

    /**
     * Returns a panel that allows the user to perform standard
     * game actions.
     * @param controller the {@link GameController} that is currently handling 
     * the ongoing game
     * @return a generic {@link Component} that embeds UI elements to 
     * perform basic game actions
     */
    StandardControlsPanel standardControlsPanel(GameController controller);
}
