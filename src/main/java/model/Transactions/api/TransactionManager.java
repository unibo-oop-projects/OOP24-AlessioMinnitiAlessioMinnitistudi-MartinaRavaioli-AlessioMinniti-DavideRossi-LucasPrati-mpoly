package model.Transactions.api;

import model.Turnation.api.Player;

public interface TransactionManager {
    void pay(Player from, Player to, int amount);
    void sell(Player owner, int amount);
    void buy(Player owner, int amount);
}
