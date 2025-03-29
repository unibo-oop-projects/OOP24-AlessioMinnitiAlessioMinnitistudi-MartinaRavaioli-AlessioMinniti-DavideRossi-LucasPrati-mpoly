package it.unibo.monopoly.model.turnation.api;

public interface Player {
    /**
     * @return
     */
    Position getPosition();
    /**
     * @param steps
     */
    void makeMove(int steps);
    /**
     * @return
     */
    boolean isAlive();
}
