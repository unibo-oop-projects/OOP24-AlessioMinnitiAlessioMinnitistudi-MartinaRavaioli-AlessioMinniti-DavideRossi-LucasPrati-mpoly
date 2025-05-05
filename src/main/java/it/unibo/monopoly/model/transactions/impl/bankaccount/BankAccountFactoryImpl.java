package it.unibo.monopoly.model.transactions.impl.bankaccount;


import java.util.function.Predicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;

/**
 * Implementation of the {@link BankAccountFactory} interface.
 */
public final class BankAccountFactoryImpl implements BankAccountFactory {

    private final int initialBalance;

    public BankAccountFactoryImpl(final int initialBalance) {
        this.initialBalance = initialBalance;
    }

    @Override
    public BankAccount createSimple(final int id, final String owner) {
        return new SimpleBankAccountImpl(id, initialBalance, owner);
    }

    @Override
    public BankAccount createWithCheck(final int id, final String owner, final Predicate<BankAccount> check) {
        return new CheckValidityBankAccount(createSimple(id, owner), 
                                            check);
    }
}
