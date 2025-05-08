package it.unibo.monopoly.model.turnation.impl;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.monopoly.model.turnation.api.Dice;


/**
 * dice implementation.
*/
public class DiceImpl implements Dice {
    private static final int FACES_OF_THE_DICE = 5;
    private final Random rand = new Random();
    private int nDices;
    private final int[] dices;
    /**
     * constructor.
     * @param n
    */
    public DiceImpl(final int n) {
        setNDices(n);
        this.dices = new int[this.nDices];
    }

    /**
     * constructor.
    */
    public DiceImpl() {
        setNDices(2);
        this.dices = new int[this.nDices];
    }
    /**
     * throw the dices.
     * @return a collection of integer
    */
    @Override
    public final Collection<Integer> throwDices() {
        return IntStream.range(0, dices.length)
                .map(i -> rand.nextInt(FACES_OF_THE_DICE) + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * set the number of dices.
     * @param value
    */
    private void setNDices(final int value) {
        this.nDices = value;
    }

    /**
     * get the number of dices.
     * @return int
    */
    @Override
    public int getNDices() {
        return this.nDices;
    }

}
