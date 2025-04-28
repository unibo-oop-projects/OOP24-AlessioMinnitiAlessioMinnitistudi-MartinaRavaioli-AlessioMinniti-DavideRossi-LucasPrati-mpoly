package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.utils.Identifiable;

/**
 * Factory interface for {@link BankAccount} objects.
 */
public interface BankAccountFactory {

    /**
     * Creates a {@link BankAccount} with an initial provided amount of money.
     * <p>
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param initialBalance the initial amount of money
     * @param owner the {@code name} of the {@link Player} that owns the {@link BankAccount} 
     * @return a new {@link BankAccount} with an initial amount of money
     * @throws IllegalArgumentException if {@code initialBalance} is negative
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    BankAccount createSimple(int id, int initialBalance, String owner);

    /**
     * Creates a {@link BankAccount} with a default, positive and non-zero amount of money.
     * <p>
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param owner the {@code name} of the {@link Player} that owns the {@link BankAccount} 
     * @return a new {@link BankAccount} with a default, positive and non-zero amount of money
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    BankAccount createSimple(int id, String owner);

    /**
     * Creates a {@link BankAccount} with an initial provided amount of money and a provided {@code check}
     * <p>
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param initialBalance the initial amount of money
     * @param owner the {@code name} of the {@link Player} that owns the {@link BankAccount} 
     * @return a new {@link BankAccount} with an initial amount of money and a provided {@code check}
     * @throws IllegalArgumentException if {@code initialBalance} is negative
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    BankAccount createWithCheck(int id, int initialBalance, String owner);


}
