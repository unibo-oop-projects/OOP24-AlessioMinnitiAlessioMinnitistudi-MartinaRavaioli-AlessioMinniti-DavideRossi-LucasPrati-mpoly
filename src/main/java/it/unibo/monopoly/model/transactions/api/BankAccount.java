package it.unibo.monopoly.model.transactions.api;

/**
 * bank account interface.
*/
public interface BankAccount {
    /**
     * @param amount
     */
    void deposit(int amount);
    /**
     * @param amount
     */
    void withdraw(int amount);

    /**
     * @return the current balance of the account
     */
    int getBalance();
}
