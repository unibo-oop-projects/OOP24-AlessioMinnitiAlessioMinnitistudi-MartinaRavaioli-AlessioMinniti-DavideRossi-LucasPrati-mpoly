package it.unibo.monopoly.model.transactions.impl.bankaccount;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.utils.api.Identifiable;

/**
 * Simple implementation of the {@link BankAccount} interface.
*/
public final class SimpleBankAccountImpl implements BankAccount {

    private static final int DEFAULT_BALANCE = 1000;
    private int balance;
    private final int id;

    /**
     * Creates a new {@link BankAccount} with an initial amount of money.
     * 
     * @param id the {@code Integer} used as the {@link Identifiable} for the {@link BankAccount}
     * Each {@link BankAccount} has an id that corresponds to a specific player.
     * @param initialBalance the initial amount of money
     * @throws IllegalArgumentException if the {@code initialBalance} is negative
     */
    public SimpleBankAccountImpl(final int id, final int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("The initial balance of the account cannot be negative");
        }

        this.balance += initialBalance;
        this.id = id;
    }

    /**
     * Creates a new {@link BankAccount} with a default, positive and non-zero amount of money.
     * 
     * @param id the {@code Integer} used as the {@link Identifiable} for the {@link BankAccount}
     * Each {@link BankAccount} has an id that corresponds to a specific player.
     */
    public SimpleBankAccountImpl(final int id) {
        this(id, DEFAULT_BALANCE);
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
    public Integer getID() {
        return id;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + balance;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleBankAccountImpl other = (SimpleBankAccountImpl) obj;
        return id == other.id
                && balance == other.balance;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", balance: " + this.balance;
    }
}
