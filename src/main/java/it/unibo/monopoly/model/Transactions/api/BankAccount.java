package it.unibo.monopoly.model.Transactions.api;

public interface BankAccount {
    void deposit(int amount);
    void withdraw(int amount);

    /**
     * @return the current balance of the account
     */
    int getBalance();
}
