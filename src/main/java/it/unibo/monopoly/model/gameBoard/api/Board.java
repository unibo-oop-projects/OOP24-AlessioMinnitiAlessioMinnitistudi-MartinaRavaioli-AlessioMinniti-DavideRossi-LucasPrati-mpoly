package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

public interface Board {
    void sellHouse(Property prop);
    void sellProperty(Property prop);
    Tile getTile(Position pos);
    void buyProperty(Property prop, Player owner);
}
