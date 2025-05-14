package it.unibo.monopoly.model.transactions.api;

import java.util.Map;
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
     * @param playerName the newOwner of the property
     * @throws IllegalStateException if the object, and the content it manages, is in
     * a state that does not allow the execution of this operation. These checks
     * are specific of the underlying implementation
     */
    void buyTitleDeed(String titleDeedName, String playerName);

    /**
     * Pay the rent for stepping on a property possessed
     * by another player.
     * @param titleDeedName the {@link TitleDeed} whose rent has to be paid,
     * The rent will vary based on the {@link RentOption} chose by the system
     * @param playerName the name associated to the {@link BankAccount}
     * of the player that has to pay the rent
     * @throws IllegalStateException if the object, and the content it manages, is in
     * a state that does not allow the execution of this operation. These checks
     * are specific of the underlying implementation
     */
    void payRent(String titleDeedName, String playerName);


    /**
     * Gets an immutable copy of a {@link BankAccount} of a specific player. 
     * @param playerName The name of the owner of the account
     * @return {@link BankAccount} of the specified player
     */
    BankAccount getBankAccount(String playerName);

    /**
     * Gets an immutable copy of a {@link TitleDeed}.
     * @param titleDeedName the name of the {@link TitleDeed} to retrieve
     * @return the {@link TitleDeed} that corresponds to the given name
     */
    TitleDeed getTitleDeed(String titleDeedName);

    /**
     * Gets a {@link Set} containing the {@link TitleDeed} {@code deeds} owned 
     * by a specific player.
     * @param ownerName The name of the player whose properties have to 
     * be retrieved
     * @return a {@link Set} with copies of the original {@link TitleDeed} objects
     */
    Set<TitleDeed> getTitleDeedsByOwner(String ownerName);

    /**
     * make a deposit from the bank to a player.
     * @param ownerName the player that will receive the payment
     * @param amount the amount of money to deposit
     */
    void receivePaymentFromBank(String ownerName, int amount);

    /**
     * make a withdraw from a player account.
     * @param ownerName the player that has to pay the bank
     * @param amount the amount of money to withdraw
     */
    void makePaymentToBank(String ownerName, int amount);

    /**
     * Ranks the players based on the state of their {@link BankAccount}
     * and the {@link TitleDeed} they own.
     * The ranking algorithm is given to the bank on construction
     * and it is responsible for calculating the total monetary 
     * value of a player.
     * In the default version of the algorithm the total monetary value
     * is given by summing the {@code balance} of the player's {@link BankAccount}
     * and the {@code mortgageValue} of each {@link TitleDeed} owned.
     * However different versions of the algorithm may give more importance
     * to a value more than another or may simply do different calculations,
     * resulting in a different ranking
     * @return a map where the keys are the players' nicknames and
     * the values are the monetary values of each player (calculated with the
     * ranking algorithm).
     * The {@link Map.Entry} are sorted based on ascending values
     */
    Map<String, Integer> rankPlayers();
}
