package model.gameBoard.api;

import model.Turnation.api.Player;

public interface Property extends Card {
    void setOwner(Player owner);
    Player getOwner();
    int getRent();
}
