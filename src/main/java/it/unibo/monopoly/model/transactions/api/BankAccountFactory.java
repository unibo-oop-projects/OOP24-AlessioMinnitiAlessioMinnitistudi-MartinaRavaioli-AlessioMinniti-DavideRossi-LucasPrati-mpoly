package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;

/**
 * Factory interface for {@link BankAccount} objects.
 */
public interface BankAccountFactory {

    /**
     * Creates a {@link BankAccount} with an initial provided amount of money.
     * <p>
     * @param initialBalance The initial amount of money
     * @param owner the name of the player that owns the {@link BankAccount} 
     * @return a new {@link SimpleBankAccountImpl} with an initial amount of money
     * @throws IllegalArgumentException if {@code initialBalance} is negative
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    BankAccount createSimple(int initialBalance, String owner);

    /**
     * Creates a {@link BankAccount} with a default, positive and non-zero amount of money.
     * <p>
     * @param owner the name of the player that owns the {@link BankAccount} 
     * @return a new {@link SimpleBankAccountImpl} with a default, positive and non-zero amount of money
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    BankAccount createSimple(String owner);


}
