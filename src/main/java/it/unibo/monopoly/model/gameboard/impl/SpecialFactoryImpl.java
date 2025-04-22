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

            TransactionManager transationM = bank;

            @Override
            public void activate(Player palyer) {
                transationM.buy(palyer, 0);
            }
            
        });
    }

    @Override
    public Special goToPrigion() {
        return new SpecialImpl(new Effect() {

            @Override
            public void activate(Player palyer) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activate'");
            }
            
        });
    }

    @Override
    public Special prigion() {
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
