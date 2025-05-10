package it.unibo.monopoly.model.turnation.api;

/**
 * player interface.
*/
public interface Player {

    String getName();
    /**
     * @return the own position
     */
    Position getPosition();
    /**
     * @param steps
     */
    void makeMove(int steps);
    /**
     * @return if is alive
     */
    boolean isAlive();
}
