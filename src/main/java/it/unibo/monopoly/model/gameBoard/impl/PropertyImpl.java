package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Player;

public class PropertyImpl implements Property {
    public PropertyImpl(){
        
    }
    @Override
    public void setOwner(Player owner) {}
    @Override
    public Player getOwner() { return null; }
    @Override
    public int getRent() { return 0; }
}
