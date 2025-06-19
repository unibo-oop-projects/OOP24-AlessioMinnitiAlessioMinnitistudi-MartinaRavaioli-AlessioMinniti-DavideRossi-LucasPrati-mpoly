package it.unibo.monopoly.model.gameboard.api;


import it.unibo.monopoly.model.turnation.api.Player;

/**
 * effect interface.
*/
public interface Effect { 

    /**
     * this method activates the effect.
     * @param player the player that triggered the effect
     */
    void activate(Player player);

    /**
     * gets a description in natural language of the effect.
     * @return a string that represents 
     */
    String getDescription();
}


