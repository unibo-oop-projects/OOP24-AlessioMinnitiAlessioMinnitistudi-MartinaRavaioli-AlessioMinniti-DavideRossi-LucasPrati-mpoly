package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.Objects;
import java.util.function.Predicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.utils.api.Identifiable;

/**
 * Simple implementation of the {@link BankAccount} interface.
*/
public final class SimpleBankAccountImpl implements BankAccount {

    private static final int DEFAULT_BALANCE = 1000;
    private final int id;
    private final Predicate<BankAccount> canContinue;
    private int balance;

    /**
     * Creates a new {@link BankAccount} with an initial amount of money.
     * 
     * @param id the {@code Integer} used as the {@link Identifiable} for the {@link BankAccount}
     * Each {@link BankAccount} has an id that corresponds to a specific player.
     * @param initialBalance the initial amount of money
     * @param canContinue strategy to determine if the {@link BankAccount}
     * can still be used to play based on its state
     */
    public SimpleBankAccountImpl(final int id, final int initialBalance, final Predicate<BankAccount> canContinue) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("The initial balance of the account cannot be negative");
        }
        Objects.requireNonNull(canContinue);
        this.balance += initialBalance;
        this.id = id;
        this.canContinue = canContinue;
    }

    /**
     * Creates a new {@link BankAccount} with a default, positive and non-zero amount of money.
     * 
     * @param id the {@code Integer} used as the {@link Identifiable} for the {@link BankAccount}
     * Each {@link BankAccount} has an id that corresponds to a specific player.
     * @param canContinue strategy to determine if the {@link BankAccount}
     * can still be used to play based on its state
     */
    public SimpleBankAccountImpl(final int id, final Predicate<BankAccount> canContinue) {
        this(id, DEFAULT_BALANCE, canContinue);
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
        return this.canContinue.test(this);
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
