package it.unibo.monopoly.model.transactions.impl.bankaccount;


import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;

/**
 * Implementation of the {@link BankAccountFactory} interface.
 */
public final class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createSimple(final int id, final int initialBalance, final String owner) {
        return new SimpleBankAccountImpl(id, initialBalance, owner);
    }

    @Override
    public BankAccount createSimple(final int id, final String owner) {
        return new SimpleBankAccountImpl(id, owner);
    }

    @Override
    public BankAccount createWithCheck(final int id, final int initialBalance, final String owner) {
        return new CheckValidityBankAccount(createSimple(id, initialBalance, owner), 
                                            accounts -> accounts.getBalance() > 0);
    }
}
