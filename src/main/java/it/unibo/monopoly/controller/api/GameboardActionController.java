package it.unibo.monopoly.controller.api;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;

public interface GameboardActionController {
    void startGame(int size, List<Player> players, List<Tile> tiles);

    void gameOver();

    void playerGameOver();

    void changePositions();

    void addHouse(Property prop);

    void addHotel(Property prop);
}
