package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.transactions.api.TransactionManager;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * transaction manager implementation.
*/
public class TransactionManagerImpl implements TransactionManager {
    @Override
    public void pay(final Player from, final Player to, final int amount) { }
    @Override
    public void sell(final Player owner, final int amount) { }
    @Override
    public void buy(final Player owner, final int amount) { }
}
