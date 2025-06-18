package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.utils.api.Identifiable;

/**
 * Factory interface for {@link BankAccount} objects.
 */
public interface BankAccountFactory {

    /**
     * Creates a {@link BankAccount} istances according to the {@link BankAccountType}.
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param type the type to create, according to {@link BankAccountType}
     * @return a new istance of {@link BankAccount} according to the {@code bankAccountType}
     */
    BankAccount createBankAccountByType(int id, BankAccountType type);

}
