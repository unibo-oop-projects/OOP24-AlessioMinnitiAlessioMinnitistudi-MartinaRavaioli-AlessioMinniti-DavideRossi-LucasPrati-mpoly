package it.unibo.monopoly.model.transactions.impl.bankaccount;

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
     * @param owner the name of the player taht owns the {@link BankAccount} 
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
     * @param owner the name of the player taht owns the {@link BankAccount}
     */
    public SimpleBankAccountImpl(final String owner) {
        this(DEFAULT_BALANCE, owner);
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
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
        if (ownerName == null) {
            if (other.ownerName != null) {
                return false;
            }
        } else if (!ownerName.equals(other.ownerName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.ownerName + ", balance: " + this.balance;
    }
}
