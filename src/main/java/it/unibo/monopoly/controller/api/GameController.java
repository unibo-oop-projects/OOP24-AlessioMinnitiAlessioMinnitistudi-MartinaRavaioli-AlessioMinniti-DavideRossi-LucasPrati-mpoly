package it.unibo.monopoly.controller.api;

import java.awt.Color;
import java.util.List;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.MainGameView;

/**
 * interface for game controller of the game.
 */
public interface GameController {
    /** 
     * End the turn of the user that's currently playing and 
     * start next player's turn.
     * If the player cannot end its turn execution will result
     * in an exception
     */
    void endTurn();

    /**
     * Throw the dices and update the position of the pawn on the gameBoard.
     */
    void throwDices();

    /**
     * Buy the property occupied by the player’s pawn
     * whose turn it is, if the property is not owned 
     * by any other player.
     */
    void buyProperty();

    /**
     * Pay the rent amount to the owner of the property
     * occupied by the player's pawn whose turn it is.
     */
    void payPropertyOwner();

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
     * Sell a {@link TitleDeed} back to the bank and refund the owner.
     * @param selectedProperty the name of the {@link TitleDeed} to sell.
     * @return whether the operation was successful or not
     */
    boolean sellProperty(TitleDeed selectedProperty);

    /**
     * gets the list of property owned by the player using the bank.
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
     * PLACEHOLDER 
     * there will be the method in Tile .
     * @param selectedProperty
     * @return an object of the class Color
     */
    Color getPropertyColor(TitleDeed selectedProperty);

    /**
     * Loads the game rules from the file
     * and asks the {@link MainGameView} to display them.
     */
    void loadRules();

    /**
     * Retrieves the player that is 
     * currently playing its turn and
     * asks the {@link MainGameView} to display
     * its information.
     */
    void loadCurrentPlayerInformation();

    /**
     * Allow to attach {@link MainGameView}s to the controller.
     * @param view the view we want to attach to this controller
     */
    void attachView(MainGameView view);

}
