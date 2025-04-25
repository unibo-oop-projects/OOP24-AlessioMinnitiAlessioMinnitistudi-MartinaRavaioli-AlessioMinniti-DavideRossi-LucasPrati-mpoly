package it.unibo.monopoly.model.turnation.api;


public interface Prisonable {

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
