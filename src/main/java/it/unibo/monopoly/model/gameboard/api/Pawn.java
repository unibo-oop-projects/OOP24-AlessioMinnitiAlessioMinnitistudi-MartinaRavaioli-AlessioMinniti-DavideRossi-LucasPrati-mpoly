package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.turnation.api.Position;

public interface Pawn {
    /**
     * @param steps
     */
    void move(int steps);
    /**
     * @return the own position
     */
    Position getPosition();
}
