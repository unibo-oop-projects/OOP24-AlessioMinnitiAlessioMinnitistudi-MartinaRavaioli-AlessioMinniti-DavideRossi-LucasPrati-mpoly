package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;

/**
 * property implementation.
*/

public class PropertyImpl implements Property {
    private static final int MAX_HOUSES=4;
    private int price;
    private boolean isOwned;
    private int rent;
    private int housePrice;
    private int hotelPrice;
    private int nHouses;
    private boolean hotel;
    //constructor
    /**
     * @param price
     */
    public PropertyImpl(final int price,final int rent,final int housePrice,final int hotelPrice) { 
        this.nHouses=0;
        this.hotel=false;
        this.isOwned=false;
        setPrice(price);
        setRent(rent);
        setHousePrice(housePrice);
        setHotelPrice(hotelPrice);
    }

    private void setRent(int rent){
        this.rent = rent;
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
        return this.rent; 
    }

    public int getNHouses(){
        return this.nHouses;
    }

    @Override
    public void buildHouse() {
        
    }

    @Override
    public int getHousePrice() {
        return this.housePrice;
    }

    @Override
    public int getHotelPrice() {
        return this.hotelPrice;
    }
}
