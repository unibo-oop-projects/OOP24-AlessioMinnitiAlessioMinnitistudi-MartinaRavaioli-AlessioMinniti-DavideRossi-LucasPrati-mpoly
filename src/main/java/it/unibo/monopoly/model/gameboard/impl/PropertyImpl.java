package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * property implementation.
*/
public class PropertyImpl extends TileImpl implements Property {
    private static final int MAX_HOUSES=4;
    private int nHouses;
    private boolean hotel;
    private String name;
    
    //constructor
    /**
     * @param price
    */
    public PropertyImpl(String name, Position id, Type type) { 
        super(id,type);
        this.nHouses=0;
        this.hotel=false;
        setName(name);
    }

    public final void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
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
