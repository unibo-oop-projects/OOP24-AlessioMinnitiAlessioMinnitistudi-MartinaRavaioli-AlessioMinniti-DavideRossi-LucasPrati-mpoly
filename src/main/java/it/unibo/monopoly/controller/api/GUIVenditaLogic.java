package it.unibo.monopoly.controller.api;

import java.awt.Color;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * this is the implementation of the logic behind the property manager GUI. 
 */
public interface GUIVenditaLogic {

    /**
     * this method removes the property fromm the property list. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param selectedProperty the property you want to sell
     * @param bank to make the transaction
     * @return wether the payment has been succesful
     */
    boolean sellProperty(TitleDeed selectedProperty, Bank bank);

    /**
     * get the selected property as a titledeed.
     * @param properties the players property list
     * @param selectedValue the property you want 
     * @return the property 
     */
    TitleDeed getProperty(List<TitleDeed> properties, Object selectedValue);

    /**
     * gets the balance of the player account using the bank.
     * @param player of which we want the balance
     * @param bank to get the balance
     * @return its balance
     */
    BankAccount getPlayerBalance(Player player, Bank bank);

    /**
     * gets the list of property owned by the player using the bank.
     * @param player of which we want the properties
     * @param bank to get the list
     * @return its property
     */
    List<TitleDeed> getProperties(Player player, Bank bank);

    /**
     * PLACEHOLDER 
     * there will be the method in Tile .
     * @param selectedProperty  of which we want the color
     * @return an object of the class Color
     */
    Color getPropertyColor(TitleDeed selectedProperty);

    /**
     * a method that gets the Ptoperty associated with a titledeed.
     * @param selectedProperty the titledeed associated
     * @param board to get the property
     * @return the property
     */
    Property getBuildableProperty(TitleDeed selectedProperty, Board board);

    /**
     * this method removes a house from the property. 
     * calls the bank method to deposit the ammount in the pleyer bank accouunt
     * @param selectedBuildableProberty the property you want to sell
     * @param bank to make the transaction
     * @param board to delete the house from the property
     * @return wether the payment has been succesful
     */
    boolean sellHouse(Property selectedBuildableProberty, Bank bank, Board board);

    /**
     * this method removes a hotel from the property.
     * calls the bank method to deposit the ammount in the pleyer bank accouunt
     * @param selectedBuildableProberty the property you want to sell
     * @param bank to make the transaction
     * @param board to delete the hotel from the property
     * @return wether the payment has been succesful
     */
    boolean sellHotel(Property selectedBuildableProberty, Bank bank, Board board);

}

