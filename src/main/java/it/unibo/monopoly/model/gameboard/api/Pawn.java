package it.unibo.monopoly.model.gameboard.api;

import java.awt.Color;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * pawn.
*/
public interface Pawn {
    /**
     * move the pawn.
     * @param steps
     */
    void move(int steps);
    /**
     * @return the own position.
    */
    Position getPosition();
    /**
     * @return the own color.
    */
    Color getColor();
}
