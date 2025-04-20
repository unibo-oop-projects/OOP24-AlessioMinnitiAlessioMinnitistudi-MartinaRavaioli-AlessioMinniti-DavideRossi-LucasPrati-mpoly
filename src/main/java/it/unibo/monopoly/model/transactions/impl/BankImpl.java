package it.unibo.monopoly.model.transactions.impl;

import java.util.List;
import java.util.Map;
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
        checkForDuplicates(accounts, BankAccount::getOwner);
        checkForDuplicates(titleDeeds, TitleDeed::getName);
        this.accounts = Maps.uniqueIndex(accounts, BankAccount::getOwner);
        this.titleDeeds = Maps.uniqueIndex(titleDeeds, TitleDeed::getName);
    }

    private <T, Y> void  checkForDuplicates(final List<T> list, final Function<? super T, Y> indexer) {
        final Multimap<Y, T> index = Multimaps.index(list, indexer::apply);
        if (index.size() < list.size()) {
            throw new IllegalArgumentException("Duplicates were found in this list, making it unusable");
        }
    }

    @Override
    public BankAccount getBankAccount(final String playerName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TitleDeed getTitleDeed(final String titleDeedName) {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public void buyTitleDeed(final String titleDeedName, final  String playerName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyTitleDeed'");
    }

}
