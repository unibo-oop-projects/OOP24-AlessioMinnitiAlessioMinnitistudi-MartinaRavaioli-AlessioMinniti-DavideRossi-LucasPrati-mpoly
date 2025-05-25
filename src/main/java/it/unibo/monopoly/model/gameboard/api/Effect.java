package it.unibo.monopoly.model.gameboard.api;


import it.unibo.monopoly.model.turnation.api.Player;

/**
 * effect interface.
*/
@FunctionalInterface
public interface Effect { 

    /**
     * this method activates the effect.
     * @param player the player tha triggered the effect
     */
    void activate(Player player);
}


