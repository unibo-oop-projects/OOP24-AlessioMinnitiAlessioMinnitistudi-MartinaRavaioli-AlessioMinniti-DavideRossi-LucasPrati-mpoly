package it.unibo.monopoly.model.gameBoard.api;

import it.unibo.monopoly.model.Turnation.api.Player;
import it.unibo.monopoly.model.Turnation.api.Position;

public interface Board {
    void sellHouse(Property prop);
    void sellProperty(Property prop);
    Card getCard(Position pos);
    void buyProperty(Property prop, Player owner);
}
