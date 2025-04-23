package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.function.BiPredicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Decorator class for {@link BankAccount} that executes 
 * a check every time a withdraw operation is performed.
 */
public final class WithdrawCheckBankAccount extends BankAccountDecorator {

    private final BiPredicate<BankAccount, Integer> withdrawCheck;

    /**
     * @param account the {@link BankAccount} to decorate
     * @param withdrawCheck the check condition that determines if the withdraw can 
     * be performed, based on the {@link BankAccount} state and the amount to withdraw
     */
    public WithdrawCheckBankAccount(final BankAccount account, final BiPredicate<BankAccount, Integer> withdrawCheck) {
        super(account);
        this.withdrawCheck = withdrawCheck;
    }

    @Override
    public void withdraw(final int amount) {
        if (!withdrawCheck.test(getAccount(), amount)) {
            throw new IllegalStateException("Cannot withdraw money because twithdraw conditions were violated");
        }
        getAccount().withdraw(amount);
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
        final WithdrawCheckBankAccount other = (WithdrawCheckBankAccount) obj;
        if (getAccount() == null) {
            if (other.getAccount() != null) {
                return false;
            }
        } else if (!getAccount().equals(other.getAccount()) || withdrawCheck.equals(other.withdrawCheck)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WithdrawCheckBankAccount [withdrawCheck=" + withdrawCheck + ", getAccount()=" + getAccount() + "]";
    }

}
