package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Player;

//interface for the Special tiles
/**
 * special interface.
 */
public interface Special extends Tile {
    //get the own effect
    /**
     * @return effect
     */
    void activateEffect(Player player);
}
