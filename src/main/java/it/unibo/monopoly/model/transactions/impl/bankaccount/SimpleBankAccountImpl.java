package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.Objects;

import com.google.common.base.Strings;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.utils.api.Identifiable;

/**
 * Simple implementation of the {@link BankAccount} interface.
*/
public final class SimpleBankAccountImpl implements BankAccount {

    private static final int DEFAULT_BALANCE = 1000;
    private int balance;
    private final int id;
    private final String ownerName;

    /**
     * Creates a new {@link BankAccount} with an initial amount of money.
     * 
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param initialBalance the initial amount of money
     * @param owner the name of the player that owns the {@link BankAccount} 
     * @throws IllegalArgumentException if the {@code initialBalance} is negative
     * @throws NullPointerException if the {@code owner} is {@code null} or {@code empty}
     */
    public SimpleBankAccountImpl(final int id, final int initialBalance, final String owner) {
        Objects.requireNonNull(Strings.emptyToNull(owner), "The name of the owner can not be null or empty");
        if (initialBalance < 0) {
            throw new IllegalArgumentException("The initial balance of the account cannot be negative");
        }

        this.balance += initialBalance;
        this.ownerName = owner;
        this.id = id;
    }

    /**
     * Creates a new {@link BankAccount} with a default, positive and non-zero amount of money.
     * 
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param owner the name of the player that owns the {@link BankAccount}
     */
    public SimpleBankAccountImpl(final int id, final String owner) {
        this(id, DEFAULT_BALANCE, owner);
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
    public String getPlayerName() {
        return this.ownerName;
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
        result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
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
                && balance == other.balance
                && ownerName.equals(other.ownerName);
    }

    @Override
    public String toString() {
        return this.ownerName + ", balance: " + this.balance + ", id: " + this.id;
    }
}
