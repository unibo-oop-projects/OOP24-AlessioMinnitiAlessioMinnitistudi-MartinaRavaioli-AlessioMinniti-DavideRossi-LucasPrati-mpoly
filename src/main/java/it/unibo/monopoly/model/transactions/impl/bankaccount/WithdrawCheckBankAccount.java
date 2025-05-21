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
            throw new IllegalStateException("Cannot withdraw money because withdraw conditions were violated");
        }
        getAccount().withdraw(amount);
    }

    @Override
    public int hashCode() {
        return getAccount().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof WithdrawCheckBankAccount other
                && this.getAccount().equals(other.getAccount());
    }

    @Override
    public String toString() {
        return "WithdrawCheckBankAccount [withdrawCheck=" + withdrawCheck + ", getAccount()=" + getAccount() + "]";
    }

}
