package it.unibo.monopoly.model.turnation.api;



public interface Parkable {

    /**
     * tells whether and how long the player is parked.
     * @return if the player is parked
     */
    boolean isParked();

    /**
     * put the player in parked status and set the turns it has to wait 
     */
    void park();

}
