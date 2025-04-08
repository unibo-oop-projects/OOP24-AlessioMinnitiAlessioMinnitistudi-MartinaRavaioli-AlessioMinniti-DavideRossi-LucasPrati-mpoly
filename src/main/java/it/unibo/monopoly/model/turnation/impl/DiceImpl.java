package it.unibo.monopoly.model.turnation.impl;

import java.util.Random;

import it.unibo.monopoly.model.turnation.api.Dice;
import org.apache.commons.lang3.tuple.Pair;


/**
 * dice implementation.
*/
public class DiceImpl implements Dice {
    Random rand = new Random();
    Pair<Integer,Integer>  result;

    @Override
    public final int throwDice() { 
        return 0;
    }

}
