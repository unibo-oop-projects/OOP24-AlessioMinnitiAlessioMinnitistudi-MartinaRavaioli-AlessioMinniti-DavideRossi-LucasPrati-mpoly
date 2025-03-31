package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * property implementation.
*/
public class PropertyImpl implements Property {
    private final Player owner;
    private final int price;
    //constructor
    /**
     * @param owner
     * @param price
     */
    public PropertyImpl(final Player owner, final int price) { 
        this.owner = owner;
        this.price = price;
    }
    //set the owner
    @Override 
    public void setOwner(final Player owner) { }
    //get the owner
    @Override
    public final Player getOwner() { 
        return this.owner; 
    }
    //get the price of the property
    @Override
    public final int getRent() { 
        return this.price; 
    }
}
