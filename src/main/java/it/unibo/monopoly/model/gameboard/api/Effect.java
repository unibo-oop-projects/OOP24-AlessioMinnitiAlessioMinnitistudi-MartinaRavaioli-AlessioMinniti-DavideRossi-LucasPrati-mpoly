package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * effect interface.
*/
public interface Effect { 

    void activate(Player player);
}
