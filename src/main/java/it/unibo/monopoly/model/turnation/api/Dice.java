package it.unibo.monopoly.model.turnation.api;

import java.util.Collection;

/**
 * dice interface.
*/
public interface Dice {
    /**
     * @return dices' result
     */
    Collection<Integer> throwDices();
}
