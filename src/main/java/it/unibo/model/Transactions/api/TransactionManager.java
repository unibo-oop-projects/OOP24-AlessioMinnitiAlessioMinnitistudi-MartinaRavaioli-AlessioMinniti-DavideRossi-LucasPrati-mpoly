package it.unibo.model.TransactionManager.API;

import it.unibo.model.Player.API.Player;

public interface TransactionManager {
    void pay(Player from, Player to, int amount);
    void sell(Player owner, int amount);
    void buy(Player owner, int amount);
}
