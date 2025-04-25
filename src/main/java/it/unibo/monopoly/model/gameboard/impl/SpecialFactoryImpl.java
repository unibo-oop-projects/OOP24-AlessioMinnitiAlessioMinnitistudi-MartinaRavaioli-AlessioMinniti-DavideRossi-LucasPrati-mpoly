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

            //DA CAMBIARE CON IL METODO PER AVERE IL NOME CHE ORA NON C'E'
            @Override
            public void activate(Player palyer) {
                transationM.depositTo(palyer.toString(), START_AMOUNT);
            }
            
        });
    }

    @Override
    public Special goToPrison() {
        return new SpecialImpl(new Effect() {

            private final static int STEPS_TO_PRISON = 13;

            @Override
            public void activate(Player palyer) {
                palyer.putInPrison();
                palyer.makeMove(STEPS_TO_PRISON);                
            }
            
        });
    }

    @Override
    public Special prison() {
        return new Special() {

            private boolean valid;
            private int steps;
            
            public void validateThrow(Pair<Integer,Integer> dices){
                if (dices.a()==dices.b()) {
                    steps = dices.a() + dices.b();
                    valid = true;
                }
                valid = false;
            }

            @Override
            public void activateEffect(Player player) {
                if (valid) {
                    player.makeMove(steps);
                }
            }
        };
    }

    @Override
    public Special parking() {
        return new SpecialImpl(new Effect() {

            @Override
            public void activate(Player palyer) {
                palyer.park();
            }
            
        });
    }

    @Override
    public Special taxes(Bank bank) {
        return new SpecialImpl(new Effect() {

            private final Bank transationM = bank;
            private final static int TAXES_AMOUNT = 100;

            //DA CAMBIARE CON IL METODO PER AVERE IL NOME CHE ORA NON C'E'
            @Override
            public void activate(Player palyer) {
                transationM.withdrawFrom(palyer.toString(), TAXES_AMOUNT);
            }
            
        });
    }

}
