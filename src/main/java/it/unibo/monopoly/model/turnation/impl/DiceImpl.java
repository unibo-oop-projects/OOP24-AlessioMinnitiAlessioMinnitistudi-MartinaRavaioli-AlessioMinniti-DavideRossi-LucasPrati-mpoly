package it.unibo.monopoly.model.turnation.impl;

import java.util.Random;

import it.unibo.monopoly.model.turnation.api.Dice;

/**
 * dice implementation.
*/
public class DiceImpl implements Dice {
    Random rand = new Random();
    // Pair<int, int> result;

    @Override
    public final int throwDice() { 
        return 0;
    }

}
