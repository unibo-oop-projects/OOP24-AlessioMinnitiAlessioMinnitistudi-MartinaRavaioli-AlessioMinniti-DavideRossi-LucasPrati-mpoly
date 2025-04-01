package it.unibo.monopoly.model.transactions.api;

/**
 * The bank account associated with each player in the game.
*/
public interface BankAccount {
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
}
