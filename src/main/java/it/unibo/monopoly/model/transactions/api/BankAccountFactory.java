package it.unibo.monopoly.model.transactions.api;

import java.util.function.Predicate;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.utils.Identifiable;

/**
 * Factory interface for {@link BankAccount} objects.
 */
public interface BankAccountFactory {

    /**
     * Creates a {@link BankAccount} with an {@code initialBalance}.
     * <p>
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param owner the name of the {@link Player} that owns the {@link BankAccount} 
     * @return a new {@link BankAccount} with an {@code initialBalance}
     * @throws IllegalArgumentException if {@code initialBalance} is negative
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    BankAccount createSimple(int id, String owner);

    /**
     * Creates a {@link BankAccount} with a provided {@code initialBalance} and a provided {@code check}.
     * <p>
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param owner the {@code name} of the {@link Player} that owns the {@link BankAccount} 
     * @param check strategy to determine if the {@link BankAccount} can still be used to play based on its state
     * @return a new {@link BankAccount} with an {@code initialBalance} and a provided {@code check}
     * @throws IllegalArgumentException if {@code initialBalance} is negative
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    BankAccount createWithCheck(int id, String owner, Predicate<BankAccount> check);


}
