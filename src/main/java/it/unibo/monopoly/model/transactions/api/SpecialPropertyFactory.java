package it.unibo.monopoly.model.transactions.api;


/**
 * interface for a factory of special property tiles. 
 * they work as a normal property tile 
 * exept for the rent that increases in a different way.
 */
public interface SpecialPropertyFactory {

    /**
     * this method returns a special property of the type station. 
     * it's rent doubles for each property of this group owned
     * @param name of the station
     * @return the special property station
     */
    TitleDeed station(String name);

    /**
     * this method returns a special property of the type society. 
     * it's rent is 5 or 10 times the dices value 
     * based on how many society the player own
     * @param name of the society
     * @return the special property society
     */
    TitleDeed society(String name);
}
