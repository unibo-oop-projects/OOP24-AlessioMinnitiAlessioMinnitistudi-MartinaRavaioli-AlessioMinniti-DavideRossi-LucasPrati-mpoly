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
     * tells whether and how long the player is parked.
     * @return if the player is parked
     */
    boolean isParked();
    /**
     * put the player in parked status and set the turns it has to wait 
     */
    void park();
    /**
     * tells whether the player is in prison.
     * @return
     */
    boolean isInPrison();
    /**
     * put the player in prison and set the turns he has to wait
     */
    void putInPrison();

}
