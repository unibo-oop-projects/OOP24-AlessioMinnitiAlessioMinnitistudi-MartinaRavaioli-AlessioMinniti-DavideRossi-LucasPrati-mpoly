package it.unibo.monopoly.view.api;

import it.unibo.monopoly.controller.api.GameController;

/**
 * Main view of the game.
 */
public interface MainView {

    /**
     * Attach the main GameController to the view.
     * @param controller 
     */
    void addGameController(GameController controller);
}
