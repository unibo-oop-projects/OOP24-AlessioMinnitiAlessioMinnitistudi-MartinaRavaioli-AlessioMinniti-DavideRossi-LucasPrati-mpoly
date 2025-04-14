
package it.unibo.monopoly.view;
import java.awt.Color;


/**
 * this class is a place holder for the actual proprieta class.
 */
public class Proprieta {

    private final Color color; 
    private final String name;
    private final int latestRent;
    private final int housePrice;
    private int houseNum;
    private final int mortage;

    /**
     * the constructor of the class initialize all the fields.
     * @param color of the property
     * @param name of the property
     * @param latestRent from the property
     * @param housePrice to build a house on the property
     * @param houseNum on the property
     * @param mortage of the property
     */
    public Proprieta(final Color color, final String name, final int latestRent, 
                            final int housePrice, final int houseNum, final int mortage) {
        this.color = color; 
        this.name = name;
        this.latestRent = latestRent;
        this.housePrice = housePrice;
        this.houseNum = houseNum;
        this.mortage = mortage;
    }

    /**
     * sets the value of how many houses are on the property.
     * @param num new house nuber
     */
    public void setHouseNum(final int num) {
        this.houseNum = num;
    }

    /**
     * .
     * @return the color of the property
     */
    public Color color() {
        return this.color;
    }

    /**
     * .
     * @return the name of the property
     */
    public String name() {
        return this.name;
    }

    /**
     * .
     * @return the latest rent from the property
     */
    public int latestRent() {
        return this.latestRent;
    }

    /**
     * .
     * @return the price of buildig a house on the property
     */
    public int housePrice() {
        return this.housePrice;
    }

    /**
     * .
     * @return the mortage of the property
     */
    public int mortage() {
        return this.mortage;
    }

    /**
     * .
     * @return how many houses are on the property
     */
    public int houseNum() {
        return this.houseNum;
    }
} 
