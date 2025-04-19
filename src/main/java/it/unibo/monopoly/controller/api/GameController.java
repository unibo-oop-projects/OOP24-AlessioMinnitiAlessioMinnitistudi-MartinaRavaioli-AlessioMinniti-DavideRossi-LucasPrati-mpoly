package it.unibo.monopoly.controller.api;

import it.unibo.monopoly.view.api.MainView;

/**
 * Main controller of the game.
 */
public interface GameController {

    /**
     * Attach the main Game view to the controller.
     * @param mainView the main view of the game
     */
    void addMainView(MainView mainView);
}
