package it.unibo.monopoly.model.turnation.impl;

import java.util.Collection;
import java.util.Random;

import it.unibo.monopoly.model.turnation.api.Dice;


/**
 * dice implementation.
*/
public class DiceImpl implements Dice {
    Random rand = new Random();
    int nDices;
    
    @Override
    public Collection<Integer> throwDices() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'throwDices'");
    }

    @Override
    public int getNDices() {
        return this.nDices;
    }

    

}
