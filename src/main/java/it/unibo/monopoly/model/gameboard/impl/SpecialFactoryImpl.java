package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Player;

public class SpecialFactoryImpl implements SpecialFactory{

    @Override
    public Special start(Bank bank) {
        return new SpecialImpl(new Effect() {

            private final Bank transationM = bank;
            private final static int START_AMOUNT = 200;

            @Override
            public void activate(Player palyer) {
                
            }
            
        });
    }

    @Override
    public Special goToPrison() {
        return new SpecialImpl(new Effect() {

            @Override
            public void activate(Player palyer) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activate'");
            }
            
        });
    }

    @Override
    public Special prison() {
        return new SpecialImpl(new Effect() {

            @Override
            public void activate(Player palyer) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activate'");
            }
            
        });
    }

    @Override
    public Special parking() {
        return new SpecialImpl(new Effect() {

            @Override
            public void activate(Player palyer) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activate'");
            }
            
        });
    }

    @Override
    public Special taxes(Bank bank) {
        return new SpecialImpl(new Effect() {

            @Override
            public void activate(Player palyer) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activate'");
            }
            
        });
    }

}
