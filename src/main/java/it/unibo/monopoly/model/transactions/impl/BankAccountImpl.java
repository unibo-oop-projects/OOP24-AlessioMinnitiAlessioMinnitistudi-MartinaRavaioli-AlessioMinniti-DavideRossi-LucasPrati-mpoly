package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Simple implementation of the bank account interface methods.
*/
public final class BankAccountImpl implements BankAccount {

    private static final int DEFAULT_BALANCE = 1000;
    private int balance;


    /**
     * Creates a new BankAccount with an initial amount of money.
     * @param initialBalance The initial amount of money
     */
    public BankAccountImpl(final int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("The initial balance of the account cannot be negative");
        }

        this.balance += initialBalance;
    }

    /**
     * Creates a BankAccount with a default, positive and non-zero amount of money.
     */
    public BankAccountImpl() {
        this(DEFAULT_BALANCE);
    }

    @Override
    public void deposit(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot receive a negative amount of money");
        }

        this.balance += amount;
    }

    @Override
    public void withdraw(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative amount of money");
        }

        this.balance -= amount;
    }

    @Override
    public int getBalance() {
        return this.balance;
    }

    @Override
    public boolean canContinue() {
        return balance > 0;
    }

    @Override
    public boolean isBankrupt() {
        return false;
    }
}
