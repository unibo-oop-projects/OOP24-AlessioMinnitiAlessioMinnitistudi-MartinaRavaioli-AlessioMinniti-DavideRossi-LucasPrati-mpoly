package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.transactions.api.Bank;

/**
 * this is SpecialFactory interface, defines which special tiles you can create 
 */
public interface SpecialFactory {

    public Special start(Bank bank);

    public Special goToPrison();

    public Special prison();

    public Special parking();

    public Special taxes(Bank bank);

}
