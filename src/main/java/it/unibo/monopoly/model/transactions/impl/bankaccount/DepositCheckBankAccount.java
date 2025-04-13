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
    public String getOwner() {
        return this.account.getOwner();
    }

}
