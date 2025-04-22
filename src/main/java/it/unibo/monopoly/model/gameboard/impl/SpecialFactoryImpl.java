package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.transactions.api.TransactionManager;
import it.unibo.monopoly.model.turnation.api.Player;

public class SpecialFactoryImpl implements SpecialFactory{

    @Override
    public Special start(TransactionManager bank) {
        return new SpecialImpl(new Effect() {

            private final TransactionManager transationM = bank;
            private final static int START_AMOUNT = 200;

            @Override
            public void activate(Player palyer) {
                transationM.sell(palyer, START_AMOUNT);
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
    public Special taxes() {
        return new SpecialImpl(new Effect() {

            @Override
            public void activate(Player palyer) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activate'");
            }
            
        });
    }

}
