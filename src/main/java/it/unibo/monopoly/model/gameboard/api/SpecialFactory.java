package it.unibo.monopoly.model.gameboard.api;

/**
 * this is SpecialFactory interface, defines which special tiles you can create 
 */
public interface SpecialFactory {

    public Special start();

    public Special goToPrison();

    public Special prison();

    public Special parking();

    public Special taxes();

}
