package it.unibo.monopoly.model.gameboard.api;


import it.unibo.monopoly.model.turnation.api.Player;

/**
 * effect interface.
*/
@FunctionalInterface
public interface Effect { 

    void activate(Player player);
}


