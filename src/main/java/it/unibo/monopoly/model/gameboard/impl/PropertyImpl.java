package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * property implementation.
*/

public class PropertyImpl extends TileImpl implements Property {
    private static final int MAX_HOUSES=4;
    private int price;
    private boolean isOwned;
    private int nHouses;
    private boolean hotel;
    
    //constructor
    /**
     * @param price
     */
    public PropertyImpl(final int price, Position id, Type type) { 
        super(id,type);
        this.nHouses=0;
        this.hotel=false;
        this.isOwned=false;
        setPrice(price);

    }

    private void setPrice(int price){
        this.price = price;
    }

    @Override
    public int getPrice(){
        return this.price;
    }

    public int getNHouses(){
        return this.nHouses;
    }

    public boolean isOwned(){
        return this.isOwned;
    }

    @Override
    public void buy(){
        this.isOwned=true;
    }

    @Override
    public void sell(){
        this.isOwned=false;
    }

    @Override
    public void buildHouse() throws Exception {
        if(this.getNHouses() < MAX_HOUSES){
            this.nHouses++;
        }
        else{
            throw new Exception("max num houses reached");
        }
    }

    @Override
    public void buildHotel() throws Exception {
        if(this.hotel == false){
            this.hotel=true;
        }
        else{
            throw new Exception("hotel already exists");
        }
    }

    public boolean hasHotel(){
        return this.hotel;
    }
}
