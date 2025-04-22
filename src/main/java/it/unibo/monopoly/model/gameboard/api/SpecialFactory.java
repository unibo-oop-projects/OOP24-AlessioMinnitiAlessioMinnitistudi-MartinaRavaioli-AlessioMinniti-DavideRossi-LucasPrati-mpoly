package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.transactions.api.TransactionManager;

/**
 * this is SpecialFactory interface, defines which special tiles you can create 
 */
public interface SpecialFactory {

    public Special start(TransactionManager bank);

    public Special goToPrigion();

    public Special prigion();

    public Special parking();

    public Special taxes();

}
