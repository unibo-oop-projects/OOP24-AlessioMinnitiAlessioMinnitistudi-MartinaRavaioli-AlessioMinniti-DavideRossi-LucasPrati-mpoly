package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.function.Predicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Decorator for {@link BankAccount} that allows 
 * to check if the {@link BankAccount} is in a state valid
 * to continue playing. The check is performed every time 
 * the canCotinue method is called
 */
public final class CheckValidityBankAccount implements BankAccount {

    private final BankAccount account;
    private final Predicate<BankAccount> canPlay;


    /**
     * @param account the {@link BankAccount} to decorate
     * @param canContinue strategy to determine if the {@link BankAccount}
     * can still be used to play based on its state
     */
    public CheckValidityBankAccount(final BankAccount account, final Predicate<BankAccount> canContinue) {
        this.account = account;
        this.canPlay = canContinue;
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
    public int getBalance() {
        return this.account.getBalance();
    }

    @Override
    public boolean canContinue() {
        return this.canPlay.test(account) && this.account.canContinue();
    }

    @Override
    public String getOwner() {
        return this.account.getOwner();
    }

}
