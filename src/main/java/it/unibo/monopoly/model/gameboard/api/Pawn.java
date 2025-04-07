package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Position;

public interface Pawn {
    void move(int steps);
    /**
     * @return the own position
     */
    Position getPosition();
    /**
     * @param steps
     */
    void makeMove(int steps);
}
