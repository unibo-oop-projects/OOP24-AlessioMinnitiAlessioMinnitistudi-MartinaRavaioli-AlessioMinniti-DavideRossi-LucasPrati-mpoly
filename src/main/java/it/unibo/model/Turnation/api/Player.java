package it.unibo.model.Turnation.api;

import it.unibo.model.Position.API.Position;

public interface Player {
    Position getPosition();
    void makeMove(int steps);
    boolean isAlive();
}
