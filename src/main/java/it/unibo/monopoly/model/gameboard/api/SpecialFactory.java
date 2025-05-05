package it.unibo.monopoly.model.gameboard.api;

import java.util.List;
import java.util.function.Function;

import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * this is SpecialFactory interface, defines which special tiles you can create 
 */
public interface SpecialFactory {

    public Special start();

    public Special goToPrison();

    public Special prison();

    public Special parking();

    public Special taxes();

    public TitleDeed Station(final String group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent,List<RentOption> additionalRentOptions);

    public TitleDeed Society(final String group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent,List<RentOption> additionalRentOptions);

}
