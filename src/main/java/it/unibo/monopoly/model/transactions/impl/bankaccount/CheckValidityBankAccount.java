package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.function.Predicate;

import it.unibo.monopoly.model.transactions.api.BankAccount;

/**
 * Decorator for {@link BankAccount} that allows 
 * to check if the {@link BankAccount} is in a state valid
 * to continue playing. The check is performed every time 
 * the canCotinue method is called
 */
public final class CheckValidityBankAccount extends BankAccountDecorator {

    private final Predicate<BankAccount> canPlay;

    /**
     * @param account the {@link BankAccount} to decorate
     * @param canContinue strategy to determine if the {@link BankAccount}
     * can still be used to play based on its state
     */
    public CheckValidityBankAccount(final BankAccount account, final Predicate<BankAccount> canContinue) {
        super(account);
        this.canPlay = canContinue;
    }

    @Override
    public boolean canContinue() {
        return this.canPlay.test(getAccount()) && getAccount().canContinue();
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
        final CheckValidityBankAccount other = (CheckValidityBankAccount) obj;
        if (getAccount() == null) {
            if (other.getAccount() != null) {
                return false;
            }
        } else if (!getAccount().equals(other.getAccount()) || canPlay.equals(other.canPlay)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CheckValidityBankAccount [canPlay=" + canPlay + ", getAccount()=" + getAccount() + "]";
    }

}
