package it.unibo.model.gameBoard.api;

import it.unibo.model.Player.API.Player;
import it.unibo.model.Position.API.Position;
import it.unibo.model.Property.API.Property;

public interface Board {
    void sellHouse(Property prop);
    void sellProperty(Property prop);
    Card getCard(Position pos);
    void buyProperty(Property prop, Player owner);
}
