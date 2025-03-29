package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.transactions.api.TransactionManager;
import it.unibo.monopoly.model.turnation.api.Player;

public class TransactionManagerImpl implements TransactionManager {
    @Override
    public void pay(Player from, Player to, int amount) { }
    @Override
    public void sell(Player owner, int amount) { }
    @Override
    public void buy(Player owner, int amount) { }
}
