package it.unibo.monopoly.view;
import java.awt.*;

public class Proprieta {

    private Color color; 
    private String name;
    private int latest_rent;
    private int house_price;
    private int house_num;
    private int mortage;

    public Proprieta (Color color, String name, int latest_rent, int house_price, int house_num, int mortage){
        this.color = color; 
        this.name = name;
        this.latest_rent = latest_rent;
        this.house_price = house_price;
        this.house_num = house_num;
        this.mortage = mortage;
    }

    public void setHouseNum(int num){
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