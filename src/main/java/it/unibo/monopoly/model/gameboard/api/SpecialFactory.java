package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * this is SpecialFactory interface, defines which special tiles you can create 
 */
public interface SpecialFactory {

    public Special start( final Bank bank);

    public Special goToPrison(Position pos, final Board board);

    public Special prison(Position pos, final Board board, final TurnationManager turnationManager);

    public Special parking(Position pos);

    public Special taxes(Position pos, final Bank bank);


}
