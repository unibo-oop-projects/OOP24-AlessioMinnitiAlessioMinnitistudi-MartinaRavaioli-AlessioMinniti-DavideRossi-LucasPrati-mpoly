package it.unibo.model.Turnation.api;

public interface Player {
    Position getPosition();
    void makeMove(int steps);
    boolean isAlive();
}
