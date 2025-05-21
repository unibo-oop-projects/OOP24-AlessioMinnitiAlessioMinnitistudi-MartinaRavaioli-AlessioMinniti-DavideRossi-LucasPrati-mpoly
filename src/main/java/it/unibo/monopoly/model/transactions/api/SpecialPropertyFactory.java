package it.unibo.monopoly.model.transactions.api;

import java.util.List;
import java.util.function.Function;

import it.unibo.monopoly.model.gameboard.impl.Group;

public interface SpecialPropertyFactory {

    public TitleDeed Station(final Group group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent);

    public TitleDeed Society(final Group group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent);
}
