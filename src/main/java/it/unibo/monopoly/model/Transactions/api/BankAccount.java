package it.unibo.monopoly.model.transactions.api;

public interface BankAccount {
    /**
     * @param amount
     */
    void deposit(int amount);
    /**
     * @param amount
     */
    void withdraw(int amount);
}
