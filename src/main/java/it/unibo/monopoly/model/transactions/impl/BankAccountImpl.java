package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * bank account implementation.
*/
public class BankAccountImpl implements BankAccount {

    private static final int DEFAULT_BALANCE=0;
    private int balance = DEFAULT_BALANCE;

    public BankAccountImpl(final int initialBalance) {
        deposit(initialBalance);
    }


    @Override
    public void deposit(final int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Cannot receive a negative amount of money");
        }

        this.balance+=amount;
    }
    @Override
    public void withdraw(final int amount) { }
    @Override
    public int getBalance() {
        return this.balance;
    }
}
