package it.unibo.monopoly.model.transactions.api;

import java.util.List;
import java.util.function.Function;

public interface SpecialPropertyFactory {

    public TitleDeed Station(final String group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent);

    public TitleDeed Society(final String group, 
                        final String name, 
                        final int salePrice, 
                        final Function<Integer, Integer> mortgageFunction, 
                        final int baseRent);
}
