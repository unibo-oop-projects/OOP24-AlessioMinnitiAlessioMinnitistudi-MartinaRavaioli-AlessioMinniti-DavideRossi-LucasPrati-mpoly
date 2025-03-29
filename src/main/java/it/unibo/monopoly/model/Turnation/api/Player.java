package it.unibo.monopoly.model.turnation.api;

public interface Player {
    Position getPosition();
    void makeMove(int steps);
    boolean isAlive();
}
