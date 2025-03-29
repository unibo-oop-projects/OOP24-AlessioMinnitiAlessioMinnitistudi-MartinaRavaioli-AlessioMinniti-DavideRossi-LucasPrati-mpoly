package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * property implementation.
*/
public class PropertyImpl implements Property {
    private Player owner;
    private int price;
    //constructor
    /**
     * 
     */
    public PropertyImpl(Player owner, int price) { 
        this.owner = owner;
        this.price = price;
    }
    //set the owner
    @Override 
    public void setOwner(final Player owner) { }
    //get the owner
    @Override
    public final Player getOwner() { 
        return null; 
    }
    //get the price of the property
    @Override
    public final int getRent() { 
        return 0; 
    }
}
