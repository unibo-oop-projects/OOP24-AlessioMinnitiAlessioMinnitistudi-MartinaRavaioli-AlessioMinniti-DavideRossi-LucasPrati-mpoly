package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.function.BiPredicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Decorator class of {@link BankAccount} that executes 
 * a check every time a deposit operation is performed.
 */
public final class DepositCheckBankAccount implements BankAccount {

    private final BankAccount account;
    private final BiPredicate<BankAccount, Integer> depositCheck;


    /**
     * @param account the {@link BankAccount} implementation to decorate
     * @param depositCheck the check condition that determines if the deposit can 
     * be performed, based on the {@link BankAccount} state and the amount to deposit
     */
    public DepositCheckBankAccount(final BankAccount account, final BiPredicate<BankAccount, Integer> depositCheck) {
        this.account = account;
        this.depositCheck = depositCheck;
    }

    @Override
    public void deposit(final int amount) {
        if (!depositCheck.test(account, amount)) {
            throw new IllegalStateException("Cannot deposit money because deposit conditions were violated");
        }
    }

    @Override
    public void withdraw(final int amount) {
        this.account.withdraw(amount);
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
    public String getPlayerName() {
        return this.account.getPlayerName();
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
        final DepositCheckBankAccount other = (DepositCheckBankAccount) obj;
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
