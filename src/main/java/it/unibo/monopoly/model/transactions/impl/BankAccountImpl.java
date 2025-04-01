package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * bank account implementation.
*/
public class BankAccountImpl implements BankAccount {

    private static final int DEFAULT_BALANCE=1000;
    private int balance = 0;

    public BankAccountImpl(final int initialBalance) {
        deposit(initialBalance);
    }

    public BankAccountImpl() {
        this.balance=DEFAULT_BALANCE;
    }

    @Override
    public void deposit(final int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Cannot receive a negative amount of money");
        }

        this.balance+=amount;
    }
    @Override
    public void withdraw(final int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative amount of money");
        }

        if(amount > balance) {
            throw new IllegalStateException("Cannot withdraw an amount of money that exceeds current available money");
        }

        this.balance-=amount;
    }
    @Override
    public int getBalance() {
        return this.balance;
    }
}
