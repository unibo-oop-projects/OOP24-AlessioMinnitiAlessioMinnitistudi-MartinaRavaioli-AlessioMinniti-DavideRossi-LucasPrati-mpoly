package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * tile interface.
 */
public interface Tile {
    /**
     * get the pawn on a tile.
     * @return Position
    */
    Position getPosition(); 

}
