package it.unibo.monopoly.view.api;

import java.util.Set;

/**
 * Display a panel to execute actions 
 * and interact with the game
 */
public interface GameActionsPanel extends InfoPanel{


    /**
     * Attach to the panel a {@link Set} of buttons 
     * that allow the user to perform the specified actions.
     * @param actions a {@link Set} of {@link GameControllerAction}.
     * Each action will be associated to a button, which will execute it 
     */
    void buildActionButtons(Set<GameControllerAction> actions);
}
