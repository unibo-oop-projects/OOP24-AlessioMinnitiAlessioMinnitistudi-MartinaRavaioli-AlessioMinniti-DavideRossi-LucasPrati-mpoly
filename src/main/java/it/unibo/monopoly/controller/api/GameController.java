package it.unibo.monopoly.controller.api;

import java.awt.Color;
import java.util.List;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * interface for game controller.
 */
public interface GameController {

    /**
     * this method returns wether there are houses on the property.
     * @param prop the property you want to know if there are houses
     * @return wether there are houses on the property
     */
    boolean areThereHouses(TitleDeed prop);

    /**
     * this method removes a house fromm the property. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedValue the property from wich the house will be removed
     * @return wether the payment has been succesful
     */
    boolean sellHouse(List<TitleDeed> properties, Object selectedValue);

    /**
     * 
     * @param properties the players property list
     * @param selectedValue the property you want 
     * @return the property 
     */
    TitleDeed getProperty(List<TitleDeed> properties, Object selectedValue);

    /**
     * this method removes the property fromm the property list. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param selectedProperty the property you want to sell
     * @return wether the payment has been succesful
     */
    boolean sellProperty(TitleDeed selectedProperty);

    /**
     * gets the list of property owned by the palyer using the bank.
     * @param player
     * @return its property
     */
    List<TitleDeed> getProperties(Player player);

    /**
     * gets the balance of the player account using the bank.
     * @param player
     * @return its balance
     */
    BankAccount getPlayerBalance(Player player);


    /**
     * PLACEHOLDER there will be the method in Tile .
     * @param selectedProperty
     * @return an object of the class Color
     */
    Color getPropertyColor(TitleDeed selectedProperty);
}
