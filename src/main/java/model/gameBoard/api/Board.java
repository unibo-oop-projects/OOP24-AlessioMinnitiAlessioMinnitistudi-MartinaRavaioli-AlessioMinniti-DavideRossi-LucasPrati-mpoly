package model.gameBoard.api;

import model.Turnation.api.Player;
import model.Turnation.api.Position;

public interface Board {
    void sellHouse(Property prop);
    void sellProperty(Property prop);
    Card getCard(Position pos);
    void buyProperty(Property prop, Player owner);
}
