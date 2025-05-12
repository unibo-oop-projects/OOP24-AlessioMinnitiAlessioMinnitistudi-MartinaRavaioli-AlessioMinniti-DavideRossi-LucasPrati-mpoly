package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.function.BiPredicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Decorator class of {@link BankAccount} that executes 
 * a check every time a deposit operation is performed.
 */
public final class DepositCheckBankAccount extends BankAccountDecorator {

    private final BiPredicate<BankAccount, Integer> depositCheck;

    /**
     * @param account the {@link BankAccount} implementation to decorate
     * @param depositCheck the check condition that determines if the deposit can 
     * be performed, based on the {@link BankAccount} state and the amount to deposit
     */
    public DepositCheckBankAccount(final BankAccount account, final BiPredicate<BankAccount, Integer> depositCheck) {
        super(account);
        this.depositCheck = depositCheck;
    }

    @Override
    public void deposit(final int amount) {
        if (!depositCheck.test(getAccount(), amount)) {
            throw new IllegalStateException("Cannot deposit money because deposit conditions were violated");
        }
        getAccount().deposit(amount);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
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
        final DepositCheckBankAccount other = (DepositCheckBankAccount) obj;
        if (getAccount() == null) {
            if (other.getAccount() != null) {
                return false;
            }
        } else if (!getAccount().equals(other.getAccount()) || depositCheck.equals(other.depositCheck)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DepositCheckBankAccount [depositCheck=" + depositCheck + ", getAccount()=" + getAccount() + "]";
    }

}
