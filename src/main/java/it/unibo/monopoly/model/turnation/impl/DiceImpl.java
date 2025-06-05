package it.unibo.monopoly.model.turnation.impl;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.monopoly.model.turnation.api.Dice;


/**
 * dice implementation.
*/
public class DiceImpl implements Dice {
    private static final int DEFAULT_FACES = 5; /**default number of faces. */
    private final int faces; /**number of faces. */
    private final Random rand = new Random(); /**random. */
    private int nDices; /**number of dices. */
    private final int[] dices; /**dices. */
    /**
     * constructor.
     * @param n number of dices
    */
    public DiceImpl(final int n) {
        setNDices(n);
        this.dices = new int[this.nDices];
        this.faces = DEFAULT_FACES;
    }
    /**
     * constructor.
     * @param n number of dices
     * @param faces number of the dices' faces
    */
    public DiceImpl(final int n, final int faces) {
        setNDices(n);
        this.dices = new int[this.nDices];
        this.faces = faces;
    }

    /**
     * constructor.
    */
    public DiceImpl() {
        setNDices(2);
        this.dices = new int[this.nDices];
        this.faces = DEFAULT_FACES;
    }

    @Override
    public final Collection<Integer> throwDices() {
        return List.of(3,2);
        /*return IntStream.range(0, dices.length)
                //.map(i -> 5)  TODO cancella il tiro prefatto 
                .map(i -> rand.nextInt(this.faces) + 1)
                .boxed()
                .collect(Collectors.toList());*/
    }

    /**
     * set the number of dices.
     * @param value value
    */
    private void setNDices(final int value) {
        this.nDices = value;
    }

    @Override
    public final int getNDices() {
        return this.nDices;
    }

}
