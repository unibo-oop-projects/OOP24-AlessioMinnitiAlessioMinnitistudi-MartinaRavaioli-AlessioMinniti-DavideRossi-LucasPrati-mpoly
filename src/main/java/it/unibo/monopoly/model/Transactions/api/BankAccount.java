package it.unibo.monopoly.model.transactions.api;

public interface BankAccount {
    void deposit(int amount);
    void withdraw(int amount);
}
