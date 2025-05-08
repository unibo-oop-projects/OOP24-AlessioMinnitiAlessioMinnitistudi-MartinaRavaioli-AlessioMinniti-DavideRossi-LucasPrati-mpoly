package it.unibo.monopoly.view.api;

/**
 * An object that encapsulates an 
 * action of the game that the current playing 
 * player can launch.
 */
public interface GameAction {

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
