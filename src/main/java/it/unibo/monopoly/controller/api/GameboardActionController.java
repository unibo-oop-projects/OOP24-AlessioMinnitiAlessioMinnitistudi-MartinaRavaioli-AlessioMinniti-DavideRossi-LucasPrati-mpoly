package it.unibo.monopoly.controller.api;

import it.unibo.monopoly.model.gameboard.api.Property;

/**
     * api for the controller of the gameboard actions.
    */
public interface GameboardActionController {
    /**
     * start the board.
     * @param size
    */
    void startGame(int size);
    /**
     * set the game over.
    */
    void gameOver();
    /**
     * remove the player who lose.
    */
    void playerGameOver();
    /**
     * change the pawns position.
    */
    void changePositions();
    /**
     * called if a player buy an house.
     * @param prop
    */
    void addHouse(Property prop);
    /**
     * called if a player buy an hotel.
     * @param prop
    */
    void addHotel(Property prop);
    
}
