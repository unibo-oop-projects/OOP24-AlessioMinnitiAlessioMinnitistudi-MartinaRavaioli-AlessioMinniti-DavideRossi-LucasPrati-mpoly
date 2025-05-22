package it.unibo.monopoly.model.turnation.api;


/**
 * interface of prisonable quality for a player.
 * if added on a player it has the possibility to end up in prison 
 * and be put in stasis for 3 turn unless 
 * he gets the same number on at least 2 dices
 */
public interface Prisonable {

    /**
     * tells whether the player is in prison.
     * @return whether the player is in prison
     */
    boolean isInPrison();

    /**
     * put the player in prison and set the turns he has to wait.
    */
    void putInPrison();

}
