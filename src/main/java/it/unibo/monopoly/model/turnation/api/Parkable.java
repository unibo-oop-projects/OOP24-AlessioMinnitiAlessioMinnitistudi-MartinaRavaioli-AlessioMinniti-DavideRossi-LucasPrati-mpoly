package it.unibo.monopoly.model.turnation.api;


/**
 * interface of parkabel quality for a player.
 * if added on a player it has the possibility to end up in the parking lot 
 * and be put in stasis for a turn
 */
public interface Parkable {

    /**
     * tells whether and how long the player is parked.
     * @return if the player is parked
     */
    boolean isParked();

    /**
     * put the player in parked status and set the turns it has to wait.
     */
    void park();

}
