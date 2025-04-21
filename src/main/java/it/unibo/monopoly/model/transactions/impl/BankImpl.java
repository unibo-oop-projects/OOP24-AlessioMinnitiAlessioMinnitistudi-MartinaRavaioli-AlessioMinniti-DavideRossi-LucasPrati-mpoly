package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * This implementation hadles all operations
 * as described in the {@link Bank} interface. 
 * It manages a {@code Collection} of {@link BankAccount} {@code objects}
 * and of {@link TitleDeed} {@code objects} 
 */
public final class BankImpl implements Bank {

    private final Map<String, BankAccount> accounts;
    private final Map<String, TitleDeed> titleDeeds;


    /**
     * Creates a new instance of {@link BankImpl} that 
     * operates with the given {@code accounts} and {@code title deeds}.
     * @param accounts the palyers' {@link BankAccount}
     * @param titleDeeds {@link List} of {@link TitleDeed} present in the game
     */
    public BankImpl(final List<BankAccount> accounts, final List<TitleDeed> titleDeeds) {
        if (accounts.isEmpty() || titleDeeds.isEmpty()) {
            throw new IllegalArgumentException("Input lists cannot be empty");
        }
        checkForDuplicates(accounts, BankAccount::getPlayerName);
        checkForDuplicates(titleDeeds, TitleDeed::getName);
        this.accounts = Maps.uniqueIndex(accounts, BankAccount::getPlayerName);
        this.titleDeeds = Maps.uniqueIndex(titleDeeds, TitleDeed::getName);
    }

    private <T, Y> void  checkForDuplicates(final List<T> list, final Function<? super T, Y> indexer) {
        final Multimap<Y, T> index = Multimaps.index(list, indexer::apply);
        if (index.size() < list.size()) {
            throw new IllegalArgumentException("Duplicates were found in this list, making it unusable");
        }
    }

    private BankAccount findAccount(String id) {
        if(!accounts.containsKey(id)) {
            throw new IllegalArgumentException("The account of the player " + id + "is not present in the system");
        }
        return accounts.get(id);
    }

    private TitleDeed findTitleDeed(String id) {
        if(!titleDeeds.containsKey(id)) {
            throw new IllegalArgumentException("The title deed " + id + "is not present in the system");
        }
        return titleDeeds.get(id);
    }

    @Override
    public void buyTitleDeed(String titleDeedName, String playerName) {
        Objects.requireNonNull(titleDeedName);
        Objects.requireNonNull(playerName);
        final BankAccount buyer = findAccount(playerName);
        final TitleDeed td = findTitleDeed(titleDeedName);

        if(td.getOwner().isPresent()) {
            throw new IllegalStateException("Property is already owned by player" + td.getOwner().get());
        }

        buyer.withdraw(td.getSalePrice());
        td.setOwner(playerName);
    }


    @Override
    public void payRent(final String titleDeedName, final String playerName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'payRent'");
    }

    @Override
    public void sellTitleDeed(final String titleDeedName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sellTitleDeed'");   
    }
}
