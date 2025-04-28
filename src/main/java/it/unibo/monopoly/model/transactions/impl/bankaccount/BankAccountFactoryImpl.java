package it.unibo.monopoly.model.transactions.impl.bankaccount;

import java.util.Objects;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;

/**
 * Implementation of the {@link BankAccountFactory} interface.
 */
public final class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createSimple(final int initialBalance, final String owner) {
        return new SimpleBankAccountImpl(initialBalance, owner);
    }

    @Override
    public BankAccount createSimple(final String owner) {
        return new SimpleBankAccountImpl(owner);
    }

    /**
     * Static factory method for creating a new {@link BankAccount} istance according to the {@code type}.
     * <p>
     * @param type the {@link BankAccountType} we want to create
     * @param initialBalance the initial amount of money
     * @param owner the name of the player that owns the {@link BankAccount} 
     * @return a new istance of {@link BankAccount} according to the {@code type}
     * @throws IllegalArgumentException if the {@code initialBalance} is negative
     * @throws NullPointerException if {@code owner} or {@code type} are {@code null}
     */
    public static BankAccount createByType(final BankAccountType type, final int initialBalance, final String owner) {
        // TODO complete with all possibilities of type
        return switch (type) {
            case CLASSIC -> new SimpleBankAccountImpl(initialBalance, owner);
            // case INFINITY  -> new InfinityBankAccountImpl(initialBalance, owner);
            default -> throw new NullPointerException("BankAccountType can not be null");
        };
    }

}
