package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.utils.api.Identifiable;

/**
 * The bank account associated with each player in the game.
*/
public interface BankAccount extends Identifiable<Integer> {
    /**
     * Increases the current amount of money in the account.
     * @param amount the amount of money to add to the account
     * @throws IllegalStateException if the underlying implementation
     * does not allow to deposit the given {@code amount} of money. This 
     * depends on the state of the {@link BankAccount} and the check
     * conditions applied by the implementation
     */
    void deposit(int amount);

    /** 
     * Decreases the current amount of money in the account.
     * @param amount the amount of money to pull from the account
     * @throws IllegalStateException if the underlying implementation
     * does not allow to pull the given {@code amount} of money. This 
     * depends on the state of the {@link BankAccount} and the check
     * conditions applied by the implementation
     */
    void withdraw(int amount);

    /**
     * @return the current balance of the account
     */
    int getBalance();

    /**
     * Checks if the account of the player is in a state 
     * valid to continue playing.
     * @return true if it can continue playing, false if the account is in a state 
     * that doesn't allow the continuation of the game 
     */
    boolean canContinue(); 
}
