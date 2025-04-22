package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.transactions.api.TransactionManager;

/**
 * this is SpecialFactory interface, defines which special tiles you can create 
 */
public interface SpecialFactory {

    public Special start(TransactionManager bank);

    public Special goToPrison();

    public Special prigsn();

    public Special parking();

    public Special taxes();

}
