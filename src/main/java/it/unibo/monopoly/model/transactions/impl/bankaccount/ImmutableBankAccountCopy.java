package it.unibo.monopoly.model.transactions.impl.bankaccount;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Immutable view for a {@link BankAccount} object.
 * The class wraps a copy of the object allowing for getter operations, but not 
 * setter operations.
 */
public final class ImmutableBankAccountCopy implements BankAccount {

    private final BankAccount account;

    /**
     * Creates a new {@link ImmutableBankAccountCopy}.
     * @param account the account to wrap and to regulate access to
     */
    public ImmutableBankAccountCopy(final BankAccount account) {
        this.account = new CheckValidityBankAccount(new SimpleBankAccountImpl(account.getID(),
                                                                            account.getBalance()),
                                                    b -> account.canContinue());
    }

    @Override
    public void deposit(final int amount) {
        throw new UnsupportedOperationException("Deposit operation is not supported on an ImmutableBankAccountView");
    }

    @Override
    public void withdraw(final int amount) {
        throw new UnsupportedOperationException("Withdraw operation is not supported on an ImmutableBankAccountView");
    }

    @Override
    public int getBalance() {
        return this.account.getBalance();
    }

    @Override
    public boolean canContinue() {
        return this.account.canContinue();
    }

    @Override
    public Integer getID() {
        return this.account.getID();
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
        final ImmutableBankAccountCopy other = (ImmutableBankAccountCopy) obj;
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
