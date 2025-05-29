package it.unibo.monopoly.model.turnation.api;

import java.util.Collection;

import it.unibo.monopoly.model.turnation.impl.DiceImpl;

/**
 * {@link DiceImpl} interface.
*/
public interface Dice {
    /**
     * return the result of the dices.
     * @return collection of integer
     */
    Collection<Integer> throwDices();
    /**
     * get the number of dices.
     * @return int
     */
    int getNDices();
}
