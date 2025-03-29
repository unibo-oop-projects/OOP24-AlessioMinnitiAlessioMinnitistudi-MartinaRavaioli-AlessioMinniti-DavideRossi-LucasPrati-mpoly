package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.model.turnation.api.Player;

public interface TransactionManager {
    /**
     * @param from
     * @param to
     * @param amount
     */
    void pay(Player from, Player to, int amount);
    /**
     * @param owner
     * @param amount
     */
    void sell(Player owner, int amount);
    /**
     * @param owner
     * @param amount
     */
    void buy(Player owner, int amount);
}
