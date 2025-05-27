package it.unibo.monopoly.model.transactions.api;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiFunction;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;

/**
 * This object handles all payment operations that happen in the game.
 * It manages a {@link Collection} of {@link BankAccount} and of {@link TitleDeed}
 * and exposes methods to perform basic payment actions in the game ({@link #payRent(String, int, Collection)},
 * {@link #buyTitleDeed(String, int)}...)
 *
*/
public interface Bank { 

    /**
     * The default ranking function used to calculate a player's
     * monetary value. This is the function that is used in the original
     * table version of the game 'Monopoly'. The final monetary value is the
     * sum of the money that is currently inside the player's bank account, 
     * and the the sum of the mortgage prices of all the title deeds it owns.
     */
    BiFunction<BankAccount, Set<TitleDeed>, Integer> DEFAULT_RANKING_FUNCTION = (b, deeds) -> {
        return b.getBalance() + deeds
                                    .stream()
                                    .mapToInt(TitleDeed::getMortgagePrice)
                                    .sum();
    };


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
     * @param dices the value of the throw used to get some specific rent
     * @throws IllegalStateException if the object, and the content it manages, is in
     * a state that does not allow the execution of this operation. These checks
     * are specific of the underlying implementation
     */
    void payRent(String titleDeedName, int playerId, Collection<Integer> dices);


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
    void depositTo(int ownerId, int amount);

    /**
     * make a withdraw from a player's {@link BankAccount}.
     * @param ownerId the player that has to pay the bank
     * @param amount the amount of money to withdraw
     */
    void withdrawFrom(int ownerId, int amount);

    /**
     * This method can be called exterally to ask the {@link Bank} which actions,
     * the specified player, can perform on the given {@link TitleDeed}. 
     * Additionally, it marks internally whether there are any actions that
     * are mandatory (meaning the player has to perform to be able to end its turn,
     * such as {@link Bank#payRent(String, int)}).
     * @param currentPlayerId the id returned by {@link PlayerImpl#getID()}
     * of the player that is currently playing its turn
     * @param titleDeedName the name  of the {@link TitleDeed}
     * associated with the {@link Property} the player's {@link Pawn}
     * has currently stepped onto.
     * @param diceThrow the total result of the dices thrown by the user, summed together. 
     * Some {@link BankAction} might utilise it to function. For instance an action that calls 
     * the method {@link Bank#payRent(String, int)}
     * @return a {@link Set} of {@link BankAction} that can, or have to be performed 
     * by the player that is currently playing its turn. The {@code actions} wil be performed
     * on the requested {@link TitleDeed}, through the {@link BankAccount} of the player whose id
     * was given.
     */
    Set<BankAction> getApplicableBankActions(int currentPlayerId, String titleDeedName, int diceThrow);
    
    /**
     * Get a {@link BankState} object that can be used to communicate with this {@link Bank} instance
     * and ask for information related to the data that it manages.
     * @return a {@link BankState} object that refers to this {@link Bank} object
     */
    BankState getBankStateObject();
}
