package it.unibo.monopoly.model.transactions.impl;

import it.unibo.monopoly.model.transactions.api.TransactionManager;
import it.unibo.monopoly.model.turnation.api.Player;

public class TransactionManagerImpl implements TransactionManager {
    public void pay(Player from, Player to, int amount) {}
    public void sell(Player owner, int amount) {}
    public void buy(Player owner, int amount) {}
}
