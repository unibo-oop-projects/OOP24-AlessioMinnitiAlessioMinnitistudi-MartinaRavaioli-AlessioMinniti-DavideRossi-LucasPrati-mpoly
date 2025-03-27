package it.unibo.monopoly.model.gameBoard.api;

import it.unibo.monopoly.model.Turnation.api.Player;

public interface Property extends Card {
    void setOwner(Player owner);
    Player getOwner();
    int getRent();
}
