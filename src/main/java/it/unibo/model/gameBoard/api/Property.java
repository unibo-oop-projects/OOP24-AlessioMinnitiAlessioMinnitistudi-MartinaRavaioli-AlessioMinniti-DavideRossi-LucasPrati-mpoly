package it.unibo.model.gameBoard.api;

import it.unibo.model.Player.API.Player;

public interface Property extends Card {
    void setOwner(Player owner);
    Player getOwner();
    int getRent();
}
