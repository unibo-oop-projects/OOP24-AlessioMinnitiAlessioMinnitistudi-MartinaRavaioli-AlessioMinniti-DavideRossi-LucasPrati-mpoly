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
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;



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
        return new SpecialImpl(new PositionImpl(0), new Effect() {

            private final Bank transactiontionM = bank;
            private final static int START_AMOUNT = 200;

            //DA CAMBIARE CON IL METODO PER AVERE IL NOME CHE ORA NON C'E'
            @Override
            public void activate(Player palyer) {
                transactiontionM.depositTo(palyer.toString(), START_AMOUNT);
            }
            
        });
    }

    @Override
    public Special goToPrison(Position pos) {
        return new SpecialImpl(pos, new Effect() {

            private final Board movementM = board;
            private final static Integer STEPS_TO_PRISON = 13;
            //fallo con la differenza 

            @Override
            public void activate(Player palyer) {
                
                palyer.putInPrison();
                movementM.movePawn(movementM., STEPS_TO_PRISON);            
            }
            
        });
    }

    @Override
    public Special prison(Position pos) {
        return new Special() {
            private final TurnationManager turnM = turnationManager;
            private final Board boarD = board;
            private int steps;
            private final Position position;

            @Override
            public void activateEffect(Player player) {
                if (turnM.moveByDices().getRight()==turnM.moveByDices().getLeft()) {
                    steps = turnM.moveByDices().getRight() + turnM.moveByDices().getLeft();
                    board.muovi //non c'è il metodo!!
                }

            @Override
            public Position getPosition() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
            }
            }
        };
    }

    @Override
    public Special parking(Position pos) {
        return new SpecialImpl(pos, new Effect() {

            @Override
            public void activate(Player palyer) {
                palyer.park();
            }
            
        });
    }

    @Override
    public Special taxes(Position pos) {
        return new SpecialImpl(pos, new Effect() {

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
