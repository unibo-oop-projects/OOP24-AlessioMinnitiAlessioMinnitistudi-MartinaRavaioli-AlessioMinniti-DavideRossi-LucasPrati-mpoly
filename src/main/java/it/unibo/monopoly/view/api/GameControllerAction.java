package it.unibo.monopoly.view.api;

import it.unibo.monopoly.controller.api.GameController;

/**
 * An object that encapsulates an 
 * action that can be called on a {@link GameController}.
 */
public interface GameControllerAction {

    /**
     * The name of the action.
     * @return a {@code String} containing the name of the action
     */
    String getName();

    /**
     * Start the execution of the object's action.
     */
    void execute();
}
