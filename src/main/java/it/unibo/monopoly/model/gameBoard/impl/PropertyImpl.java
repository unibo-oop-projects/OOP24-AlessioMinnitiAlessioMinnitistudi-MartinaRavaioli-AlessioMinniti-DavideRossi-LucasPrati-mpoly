package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Player;

//property implementation
public class PropertyImpl implements Property {
    //constructor
    /**
     * 
     */
    public PropertyImpl() { }
    //set the owner
    @Override 
    public void setOwner(final Player owner) { }
    //get the owner
    @Override
    public final Player getOwner() { return null; }
    //get the price of the property
    @Override
    public final int getRent() { return 0; }
}
