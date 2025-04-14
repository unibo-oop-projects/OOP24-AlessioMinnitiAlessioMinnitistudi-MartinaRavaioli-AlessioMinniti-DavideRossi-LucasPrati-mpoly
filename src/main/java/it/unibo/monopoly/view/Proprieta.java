package it.unibo.monopoly.view;
import java.awt.Color;

public class Proprieta {

    private final Color color; 
    private final String name;
    private final int latest_rent;
    private final int house_price;
    private int house_num;
    private final int mortage;

    public Proprieta (final Color color, final String name, final int latest_rent, final int house_price, final int house_num, final int mortage){
        this.color = color; 
        this.name = name;
        this.latest_rent = latest_rent;
        this.house_price = house_price;
        this.house_num = house_num;
        this.mortage = mortage;
    }

    public void setHouseNum(final int num){
        this.house_num=num;
    }

    public Color color(){
        return this.color;
    }

    public String name(){
        return this.name;
    }

    public int latest_rent(){
        return this.latest_rent;
    }

    public int house_price(){
        return this.house_price;
    }

    public int mortage(){
        return this.mortage;
    }

    public int house_num(){
        return this.house_num;
    }
} 