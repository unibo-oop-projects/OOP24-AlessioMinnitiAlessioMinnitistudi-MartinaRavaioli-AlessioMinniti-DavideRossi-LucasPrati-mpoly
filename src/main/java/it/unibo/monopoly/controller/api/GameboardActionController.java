package it.unibo.monopoly.controller.api;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;

/**
     * api for the controller of the gameboard actions.
    */
public interface GameboardActionController {
    /**
     * start the board.
     * @param size
     * @param players
     * @param tiles
     * @param dice
     * @param pawns
    */
    void startGame(int size, List<Player> players, List<Tile> tiles, Dice dice, List<Pawn> pawns);
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
