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

    @Override
    public int getBalance() {
        return this.account.getBalance();
    }

    @Override
    public String getPlayerName() {
        return this.account.getPlayerName();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
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
        final BankAccountDecorator other = (BankAccountDecorator) obj;
        if (account == null) {
            if (other.account != null) {
                return false;
            }
        } else if (!account.equals(other.account)) {
            return false;
        }
        return true;
    }
}
