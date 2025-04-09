package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * transaction manager interface.
*/
public interface Bank {
    
    /**
     * Sells a property contract associated with a specific
     * {@link BankAccount}.
     * The property contract becomes available for purchase
     * and the previous owner is refunded
     * @param propertyName the name of the property to sell
     */
    void sellProperty(String propertyName);
    /**
     * Purchase a property contract for a specifc {@link BankAccount}
     * @param propertyName the name of the property contract to be purchased 
     * @param newOwner the newOwner of the property
     */
    void buyProperty(String propertyName, Player newOwner);     

    /**
     * Trade an amount of money from a player's {@link BankAccount} to another.
     * @param sender the name of the player whose {@link BankAccount} sends the money
     * @param receiver the name of player whose {@link BankAccount} receives the money
     * @param amount the amount of money that is exchanged
     */
    void executePayment(String sender, String receiver, int amount);

    
}
