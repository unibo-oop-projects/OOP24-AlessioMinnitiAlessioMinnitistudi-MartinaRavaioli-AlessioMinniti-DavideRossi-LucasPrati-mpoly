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
     * Run the object's action on the {@code controller} given as input.
     * @param controller The {@link GameController} on which commands will be called.
     * The action will execute commands on the controller given as input.
     */
    void execute(GameController controller);
}
