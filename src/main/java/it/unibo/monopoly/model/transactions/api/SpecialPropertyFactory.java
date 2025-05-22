package it.unibo.monopoly.model.transactions.api;

import java.util.function.Function;

import it.unibo.monopoly.model.gameboard.impl.Group;

/**
 * interface for a factory of special property tiles. 
 * they work as a normal property tile 
 * exept for the rent that increases in a different way.
 */
public interface SpecialPropertyFactory {

    /**
     * this method returns a special property of the type station. 
     * it's rent doubles for each property of this group owned
     * @param group of the station
     * @param name of the station
     * @param salePrice of the station
     * @param mortgageFunction of the station
     * @param baseRent of the station
     * @return the special property station
     */
    TitleDeed station(Group group, 
                        String name, 
                        int salePrice, 
                        Function<Integer, Integer> mortgageFunction, 
                        int baseRent);

    /**
     * this method returns a special property of the type society. 
     * it's rent is 5 or 10 times the dices value 
     * based on how many society the player own
     * @param group of the society
     * @param name of the society
     * @param salePrice of the society
     * @param mortgageFunction of the society
     * @param baseRent of the society
     * @return the special property society
     */
    TitleDeed society(Group group, 
                        String name, 
                        int salePrice, 
                        Function<Integer, Integer> mortgageFunction, 
                        int baseRent);
}
