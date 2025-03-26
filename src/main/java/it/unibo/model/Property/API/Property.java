package it.unibo.model.Property.API;

public interface Property extends Card {
    void setOwner(Player owner);
    Player getOwner();
    int getRent();
}
