package it.unibo.monopoly.model.turnation.api;

/**
 * player interface.
*/
public interface Player {
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
    /**
     * moves the player to the special tile Prison
     */
    void sendToPrison();
    /**
     * 
     * @return whether the palyer is still in prison
     */
    boolean isInPrison();
}
