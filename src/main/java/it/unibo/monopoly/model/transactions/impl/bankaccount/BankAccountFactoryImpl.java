package it.unibo.monopoly.model.transactions.impl.bankaccount;


import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;

/**
 * Implementation of the {@link BankAccountFactory} interface.
 */
public final class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createSimple(int id, int initialBalance, String owner) {
        return new SimpleBankAccountImpl(id, initialBalance, owner);
    }

    @Override
    public BankAccount createSimple(int id, String owner) {
        return new SimpleBankAccountImpl(id, owner);
    }

    @Override
    public BankAccount createWithCheck(int id, int initialBalance, String owner) {
        return new CheckValidityBankAccount(createSimple(id, initialBalance, owner), 
                                            accounts -> accounts.getBalance() > 0);
    }
}
