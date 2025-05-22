package it.unibo.monopoly.model.transactions.api;

import java.util.Set;

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
     * @throws IllegalStateException if the object, and the content it manages, is in
     * a state that does not allow the execution of this operation. These checks
     * are specific of the underlying implementation
     */
    void sellTitleDeed(String titleDeedName);
    /**
     * Purchase a {@link TitleDeed} for a specifc player.
     * @param titleDeedName the name of the {@link TitleDeed} to be purchased 
     * @param playerId the id associated with the player that will be the new owner of the property
     * The id is used to retrieve the account of the player and make the purchase
     * @throws IllegalStateException if the object, and the content it manages, is in
     * a state that does not allow the execution of this operation. These checks
     * are specific of the underlying implementation
     */
    void buyTitleDeed(String titleDeedName, int playerId);

    /**
     * Pay the rent for stepping on a property possessed
     * by another player.
     * @param titleDeedName the {@link TitleDeed} whose rent has to be paid,
     * The rent will vary based on the {@link RentOption} chose by the system
     * @param playerId the id, returned by {@link it.unibo.monopoly.utils.Identifiable#getID()}, 
     * associated to the {@link BankAccount} of the player that has to pay the rent
     * @throws IllegalStateException if the object, and the content it manages, is in
     * a state that does not allow the execution of this operation. These checks
     * are specific of the underlying implementation
     */
    void payRent(String titleDeedName, int playerId);


    /**
     * Gets an immutable copy of a {@link BankAccount} of a specific player. 
     * @param playerId The id, returned by {@link it.unibo.monopoly.utils.Identifiable#getID()},
     * of the owner of the account
     * @return {@link BankAccount} of the specified player
     */
    BankAccount getBankAccount(int playerId);

    /**
     * Gets an immutable copy of a {@link TitleDeed}.
     * @param titleDeedName the name of the {@link TitleDeed} to retrieve
     * @return the {@link TitleDeed} that corresponds to the given name
     */
    TitleDeed getTitleDeed(String titleDeedName);

    /**
     * Gets a {@link Set} containing the {@link TitleDeed} {@code deeds} owned 
     * by a specific player.
     * @param ownerId The id,, returned by {@link it.unibo.monopoly.utils.Identifiable#getID()},
     * of the player whose properties have to be retrieved
     * @return a {@link Set} with copies of the original {@link TitleDeed} objects
     */
    Set<TitleDeed> getTitleDeedsByOwner(int ownerId);

    /**
     * make a deposit from the bank to a player's {@link BankAccount}.
     * @param ownerId the player that will receive the payment
     * @param amount the amount of money to deposit
     */
    void receivePaymentFromBank(int ownerId, int amount);

    /**
     * make a withdraw from a player's {@link BankAccount}.
     * @param ownerId the player that has to pay the bank
     * @param amount the amount of money to withdraw
     */
    void makePaymentToBank(int ownerId, int amount);
}
