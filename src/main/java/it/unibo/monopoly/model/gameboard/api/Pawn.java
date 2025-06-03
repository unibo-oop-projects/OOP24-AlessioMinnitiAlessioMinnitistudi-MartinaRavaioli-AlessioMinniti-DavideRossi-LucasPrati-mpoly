package it.unibo.monopoly.model.gameboard.api;

import java.awt.Color;

import it.unibo.monopoly.model.turnation.api.Position;

/**
 * pawn.
*/
public interface Pawn {
    /**
     * move the pawn.
     * @param steps number of steps to do
     */
    void move(int steps);
    /**
     * get his position.
     * @return the own position.
    */
    Position getPosition();
    /**
     * get his previous position.
     * @return the own previous position.
    */
    Position getPreviousPosition();
    /**
     * get his color.
     * @return the own color.
    */
    Color getColor();

    /**
     * set the position.
     * @param pos position
    */
    void setPosition(Position pos);
    /**
     * get the shape.
     * @return String
    */
    String getShape();
    /**
     * set the shape.
     * @param shape shape
    */
    void setShape(String shape);
    /**
     * set the color.
     * @param color color
    */
    void setColor(Color color);
}
