package it.unibo.monopoly.model.transactions.api;

import java.util.function.Predicate;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.utils.api.Identifiable;

/**
 * Factory interface for {@link BankAccount} objects.
 */
public interface BankAccountFactory {

    /**
     * Creates a {@link BankAccount} with an {@code initialBalance} and an {@code "always true"} check.
     * 
     * @param id the {@link Identifiable} representing the {@link Player} that owns the {@link BankAccount} 
     * @return a new {@link BankAccount} with an {@code initialBalance}
     */
    BankAccount createSimple(int id);

    /**
     * Creates a {@link BankAccount} with a provided {@code initialBalance} and a provided {@code check}.
     * 
     * @param id the {@link Identifiable} representing the {@link Player} that owns the {@link BankAccount}
     * @param check strategy to determine if the {@link BankAccount} can still be used to play based on its state
     * @return a new {@link BankAccount} with an {@code initialBalance} and a provided {@code check}
     */
    BankAccount createWithCheck(int id, Predicate<BankAccount> check);


}
