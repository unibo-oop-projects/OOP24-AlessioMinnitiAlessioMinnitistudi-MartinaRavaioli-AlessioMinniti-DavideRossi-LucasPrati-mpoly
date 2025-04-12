package it.unibo.monopoly.model.transactions.impl.bankAccounts;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Simple implementation of the {@link BankAccount} interface.
*/
public final class SimpleBankAccountImpl implements BankAccount {

    private static final int DEFAULT_BALANCE = 1000;
    private int balance;
    private final String ownerName;


    /**
     * Creates a new BankAccount with an initial amount of money.
     * @param initialBalance The initial amount of money
     */
    public SimpleBankAccountImpl(final int initialBalance, final String owner) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("The initial balance of the account cannot be negative");
        }

        this.balance += initialBalance;
        this.ownerName = owner;
    }

    /**
     * Creates a BankAccount with a default, positive and non-zero amount of money.
     */
    public SimpleBankAccountImpl(final String owner) {
        this(DEFAULT_BALANCE,owner);
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
        return true;
    }

    @Override
    public String getOwner() {
        return this.ownerName;
    }
}
