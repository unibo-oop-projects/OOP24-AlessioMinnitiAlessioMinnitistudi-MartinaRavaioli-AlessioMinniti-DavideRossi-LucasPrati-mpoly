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
    private int housePrice;
    private int hotelPrice;
    private int nHouses;
    private boolean hotel;
    
    //constructor
    /**
     * @param price
     */
    public PropertyImpl(final int price,final int housePrice,final int hotelPrice, Position id, Type type) { 
        super(id,type);
        this.nHouses=0;
        this.hotel=false;
        this.isOwned=false;
        setPrice(price);
        setHousePrice(housePrice);
        setHotelPrice(hotelPrice);
    }

    private void setPrice(int price){
        this.price = price;
    }

    private void setHousePrice(int housePrice){
        this.housePrice = housePrice;
    }
    
    private void setHotelPrice(int hotelPrice){
        this.hotelPrice = hotelPrice;
    }
    
    @Override
    public int getPrice(){
        return this.price;
    }

    //get the price of the property
    @Override
    public final int getRent() { 
        return this.price + this.housePrice * this.nHouses + (this.hotel ? this.hotelPrice : 0);
    }

    public int getNHouses(){
        return this.nHouses;
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
    public int getHousePrice() {
        return this.housePrice;
    }

    @Override
    public int getHotelPrice() {
        return this.hotelPrice;
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
