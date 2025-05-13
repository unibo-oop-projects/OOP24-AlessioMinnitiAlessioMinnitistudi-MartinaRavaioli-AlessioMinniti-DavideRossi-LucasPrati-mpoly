package it.unibo.monopoly.model.gameboard.api;

import java.util.List;
import java.util.function.Function;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * this is SpecialFactory interface, defines which special tiles you can create 
 */
public interface SpecialFactory {

    public Special start();

    public Special goToPrison(Position pos);

    public Special prison(Position pos);

    public Special parking(Position pos);

    public Special taxes(Position pos);


}
