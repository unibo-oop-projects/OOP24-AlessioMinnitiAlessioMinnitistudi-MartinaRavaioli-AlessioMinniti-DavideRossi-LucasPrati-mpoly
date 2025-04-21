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

}
