package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.utils.api.Identifiable;

/**
 * The bank account associated with each player in the game.
*/
public interface BankAccount extends Identifiable<Integer> {
    /**
     * Increases the current amount of money in the account.
     * @param amount the amount of money to add to the account
     */
    void deposit(int amount);

    /** 
     * Decreases the current amount of money in the account.
     * @param amount the amount of money to pull from the account
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
