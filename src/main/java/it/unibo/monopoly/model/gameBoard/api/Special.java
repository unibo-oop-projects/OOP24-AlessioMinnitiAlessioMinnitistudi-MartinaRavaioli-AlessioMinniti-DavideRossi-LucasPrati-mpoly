package it.unibo.monopoly.model.gameboard.api;

//interface for the Special tiles
/**
 * special interface.
 */
public interface Special extends Tile {
    //get the own effect
    /**
     * @return effect
     */
    Effect getEffect();
}
