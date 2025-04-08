package it.unibo.monopoly.model.turnation.impl;

import java.util.Collection;
import java.util.Random;

import it.unibo.monopoly.model.turnation.api.Dice;


/**
 * dice implementation.
*/
public class DiceImpl implements Dice {
    private Random rand = new Random();
    private int nDices;
    private int dices[];
    private int currDice;

    public DiceImpl(int n){
        this.currDice=0;
        setNDices(n);
        dices=new int[this.nDices];
    }

    public DiceImpl(){
        this.currDice=0;
        setNDices(2);
        dices=new int[this.nDices];
    }
    
    @Override
    public Collection<Integer> throwDices() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'throwDices'");
    }

    private void setNDices(int value){
        this.nDices=value;
    }

    @Override
    public int getNDices() {
        return this.nDices;
    }

    

}
