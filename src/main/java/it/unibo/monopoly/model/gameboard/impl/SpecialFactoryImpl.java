package it.unibo.monopoly.model.gameboard.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.RentOption;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;



public class SpecialFactoryImpl implements SpecialFactory{

    private final Bank bank;
    private final TurnationManager turnationManager;
    private final Board board;

    public SpecialFactoryImpl(Bank bank, TurnationManager turnationManager, Board board) {
        this.turnationManager = turnationManager;
        this.bank = bank;
        this.board = board;
    }

    @Override
    public Special start() {
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
            //fallo con la differenza 

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
            private final TurnationManager turnM = turnationManager;
            private final Board boarD = board;
            private int steps;

            @Override
            public void activateEffect(Player player) {
                if (turnM.moveByDices().getRight()==turnM.moveByDices().getLeft()) {
                    steps = turnM.moveByDices().getRight() + turnM.moveByDices().getLeft();
                    board.muovi //non c'è il metodo!!
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
    public Special taxes() {
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
