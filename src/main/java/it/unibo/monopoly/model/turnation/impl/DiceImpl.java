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
    private Random rand = new Random();
    private int nDices;
    private int dices[];

    public DiceImpl(int n){
        setNDices(n);
        dices=new int[this.nDices];
    }

    public DiceImpl(){
        setNDices(2);
        dices=new int[this.nDices];
    }
    
    @Override
    public Collection<Integer> throwDices() {
        return IntStream.range(0, dices.length)
                .map(i -> rand.nextInt(5) + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    private void setNDices(int value){
        this.nDices=value;
    }

    @Override
    public int getNDices() {
        return this.nDices;
    }

    

}
