package it.unibo.monopoly.model.transactions.impl.bankaccount;


import java.util.Objects;
import java.util.function.Predicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;

/**
 * Implementation of the {@link BankAccountFactory} interface.
 */
public final class BankAccountFactoryImpl implements BankAccountFactory {

    private final int initialBalance;

    /**
     * Creates a new {@link BankAccountFactoryImpl}. Based on the given {@code initialBalance}.
     * 
     * @param initialBalance the initial amount of money of each {@link BankAccount}
     * @throws IllegalArgumentException if {@code initialBalance} is negative
     */
    public BankAccountFactoryImpl(final int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("The initial balance of the account cannot be negative");
        }
        this.initialBalance = initialBalance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BankAccount createSimple(final int id, final String owner) {
        return new SimpleBankAccountImpl(id, initialBalance, owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BankAccount createWithCheck(final int id, final String owner, final Predicate<BankAccount> check) {
        Objects.requireNonNull(check, "Check cannot be null");
        return new CheckValidityBankAccount(createSimple(id, owner), 
                                            check);
    }
}
