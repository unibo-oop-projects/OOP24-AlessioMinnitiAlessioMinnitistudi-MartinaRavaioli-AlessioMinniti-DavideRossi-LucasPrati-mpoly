package it.unibo.monopoly.model.transactions.impl.bankaccount;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Abstract class the defines the behaviours 
 * common to all decorators for {@link SimpleBankAccountImpl}.
 */
abstract class BankAccountDecorator implements BankAccount {

    private final BankAccount account;

    protected BankAccountDecorator(final BankAccount account) {
        this.account = account;
    }

    protected BankAccount getAccount() {
        return this.account;
    }

    @Override
    public int getBalance() {
        return this.account.getBalance();
    }

    @Override
    public void deposit(final int amount) {
        this.account.deposit(amount);
    }

    @Override
    public void withdraw(final int amount) {
        this.account.withdraw(amount);
    }

    @Override
    public boolean canContinue() {
        return this.account.canContinue();
    }

    @Override
    public Integer getID() {
        return this.account.getID();
    }
}
