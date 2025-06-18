package it.unibo.monopoly.view.api;

import java.util.Set;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.transactions.api.PropertyActionsEnum;

/**
 * Display a panel to execute actions 
 * and interact with the game.
 */
public interface GameActionsPanel extends GamePanel {


    /**
     * Attach to the panel a {@link Set} of buttons 
     * that allow the user to perform the specified actions.
     * @param controller the {@link GameController} on which actions will be called
     * @param actionNames the names of the actions the player can execute.
     * Each action will be associated to a button, which will execute it 
     */
    void buildActionButtons(Set<PropertyActionsEnum> actionNames, GameController controller);
}
