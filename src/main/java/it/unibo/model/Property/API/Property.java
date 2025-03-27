package it.unibo.model.Property.API;

import it.unibo.model.Player.API.Player;
import it.unibo.model.gameBoard.api.Card;

public interface Property extends Card {
    void setOwner(Player owner);
    Player getOwner();
    int getRent();
}
