package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

public interface Property extends Tile {
    void setOwner(Player owner);
    Player getOwner();
    int getRent();
}
