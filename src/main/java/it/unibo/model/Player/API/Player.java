package it.unibo.model.Player.API;

import it.unibo.model.Position.API.Position;

public interface Player {
    Position getPosition();
    void makeMove(int steps);
    boolean isAlive();
}
