package it.unibo.monopoly.model.gameboard.api;

import java.awt.Color;

import it.unibo.monopoly.model.turnation.api.Position;
/**
 * pawn factory interface.
*/
public interface PawnFactory {
    /**
     * method to create basic pawns.
     * @param id
     * @param pos
     * @param color
     * @return Pawn
    */
    Pawn createBasic(int id, Position pos, Color color);
    /**
     * method to create basic pawns.
     * @param id
     * @param pos
     * @param color
     * @param shape
     * @return Pawn
    */
    Pawn createAdvanced(int id, Position pos, Color color, String shape);
}
