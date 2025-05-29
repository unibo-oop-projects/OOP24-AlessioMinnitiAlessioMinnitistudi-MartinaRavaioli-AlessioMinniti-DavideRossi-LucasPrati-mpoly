package it.unibo.monopoly.controller.api;

import java.awt.Color;
import java.util.List;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * this is the implementation of the logic behind the property manager GUI. 
 */
public interface GUI_VenditaLogic {

    /**
     * this method returns wether there are houses on the property.
     * @param prop the property you want to know if there are houses
     * @return wether there are houses on the property
     */
    boolean areThereHouses(final TitleDeed prop);

    /**
     * this method removes a house fromm the property. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedValue the property from wich the house will be removed
     * @return wether the payment has been succesful
     */
    boolean sellHouse(final List<TitleDeed> properties, final Object selectedValue);

    /**
     * this method removes the property fromm the property list. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedProperty the property you want to sell
     * @return wether the payment has been succesful
     */
    boolean sellProperty(final List<TitleDeed> properties, final TitleDeed selectedProperty);

    /**
     * 
     * @param properties the players property list
     * @param selectedValue the property you want 
     * @return the property 
     */
    TitleDeed getProperty(final List<TitleDeed> properties, final Object selectedValue);

        /**
     * gets the balance of the player account using the bank.
     * @param player
     * @return its balance
     */
    BankAccount getPlayerBalance(Player player);

    /**
     * gets the list of property owned by the player using the bank.
     * @param player
     * @return its property
     */
    List<TitleDeed> getProperties(Player player);

    /**
     * PLACEHOLDER 
     * there will be the method in Tile .
     * @param selectedProperty
     * @return an object of the class Color
     */
    Color getPropertyColor(TitleDeed selectedProperty);

}

