package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

//interface for the Special tiles
/**
 * special interface.
 */
public interface Special extends Tile {
    //get the own effect
    /**
     * this method activates the effect of this special card.
     * @param player the player that triggered the effect
     */
    void activateEffect(Player player);
}
