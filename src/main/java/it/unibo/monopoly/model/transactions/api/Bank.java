package it.unibo.monopoly.model.transactions.api;

/**
 * transaction manager interface.
*/
public interface Bank { 
    /**
     * Sells a {@link TitleDeed} associated with a specific
     * {@link BankAccount}.
     * The {@link TitleDeed} becomes available for purchase
     * and the previous owner is refunded
     * @param titleDeedName the name of the deed to sell
     */
    void sellTitleDeed(String titleDeedName);
    /**
     * Purchase a {@link TitleDeed} for a specifc player.
     * @param titleDeedName the name of the {@link TitleDeed} to be purchased 
     * @param playerName the newOwner of the property
     */
    void buyTitleDeed(String titleDeedName, String playerName);

    /**
     * Pay the rent for stepping on a property possessed
     * by another player.
     * @param playerName the name associated to the {@link BankAccount}
     * of the player that has to pay the rent
     * @param titleDeedName the {@link TitleDeed} whose rent has to be paid,
     * The rent will vary based on the {@link RentOption} chose by the system
     */
    void payRent(String playerName, String titleDeedName);


    /**
     * Get a copy of a {@link BankAccount} of a specific player. 
     * @param playerName The name of the owner of the account
     * @return {@link BankAccount} of the specified player
     */
    BankAccount getBankAccount(String playerName);
}
