package it.unibo.model.Property.API;

import it.unibo.model.Card.API.Card;
import it.unibo.model.Player.API.Player;

public interface Property extends Card {
    void setOwner(Player owner);
    Player getOwner();
    int getRent();
}
