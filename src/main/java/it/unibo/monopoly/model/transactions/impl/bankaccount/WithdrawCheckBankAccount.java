package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.function.BiPredicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Decorator class for {@link BankAccount} that executes 
 * a check every time a withdraw operation is performed.
 */
public final class WithdrawCheckBankAccount implements BankAccount {

    private final BankAccount account;
    private final BiPredicate<BankAccount, Integer> withdrawCheck;

    /**
     * @param account the {@link BankAccount} to decorate
     * @param withdrawCheck the check condition that determines if the withdraw can 
     * be performed, based on the {@link BankAccount} state and the amount to withdraw
     */
    public WithdrawCheckBankAccount(final BankAccount account, final BiPredicate<BankAccount, Integer> withdrawCheck) {
        this.account = account;
        this.withdrawCheck = withdrawCheck;
    }

    @Override
    public void deposit(final int amount) {
        this.account.deposit(amount);
    }

    @Override
    public void withdraw(final int amount) {
        if (!withdrawCheck.test(account, amount)) {
            throw new IllegalStateException("Cannot withdraw money because twithdraw conditions were violated");
        }
        this.account.withdraw(amount);
    }

    @Override
    public int getBalance() {
        return account.getBalance();
    }

    @Override
    public boolean canContinue() {
        return account.canContinue();
    }

    @Override
    public String getPlayerName() {
        return account.getPlayerName();
    }

    @Override
    public String toString() {
        return this.account.toString();
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
        final WithdrawCheckBankAccount other = (WithdrawCheckBankAccount) obj;
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
