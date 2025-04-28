package it.unibo.monopoly.model.transactions.impl.bankaccount;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;

/**
 * Implementation of the {@link BankAccountFactory} interface.
 */
public final class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createSimple(final int initialBalance, final String owner) {
        return new SimpleBankAccountImpl(initialBalance, owner);
    }

    @Override
    public BankAccount createSimple(final String owner) {
        return new SimpleBankAccountImpl(owner);
    }

}
