package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.model.gameboard.api.Property;

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
     * Purchase a property contract for a specifc {@link BankAccount}.
     * @param propertyName the name of the property contract to be purchased 
     * @param playerName the newOwner of the property
     */
    void buyProperty(String propertyName, String playerName);

    /**
     * Pay the rent for stepping on a property possessed
     * by another player.
     * @param playerName the name associated to the {@link BankAccount}
     * of the player that has to pay the rent
     * @param property the {@link Property} whose rent has to be paid,
     * based on the state of the property the amount of money
     * due to pay will vary as described in the {@link TitleDeed} of 
     * the respective {@link Property}
     */
    void payRent(String playerName, Property property);


    /**
     * Get a copy of a {@link BankAccount} of a specific player. 
     * @param playerName The name of the owner of the account
     * @return {@link BankAccount} of the specified player
     */
    BankAccount getBankAccount(String playerName);
}
