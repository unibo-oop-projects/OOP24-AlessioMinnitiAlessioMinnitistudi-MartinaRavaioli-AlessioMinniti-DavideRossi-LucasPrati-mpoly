package it.unibo.model.Player.API;

public interface Player {
    Position getPosition();
    void makeMove(int steps);
    boolean isAlive();
}
