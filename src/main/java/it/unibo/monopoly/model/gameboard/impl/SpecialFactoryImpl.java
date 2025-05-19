package it.unibo.monopoly.model.gameboard.impl;


import java.util.Collection;
import java.util.Set;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
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

        return new SpecialImpl("Start", new PositionImpl(0), Type.SPECIAL, new Effect() {

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
        return new SpecialImpl("GoToPrison", pos, Type.SPECIAL, new Effect() {

            private final Board movementM = board;
            //TODO fallo con la differenza !!

            @Override
            public void activate(Player palyer) {
                palyer.putInPrison();
                movementM.movePawn( movementM.getPawn(palyer.getID()), Set.of(1 - pos.getPos()) );            
            }
            
        });
    }

    @Override
    public Special prison(Position pos) {
        return new SpecialImpl(null, pos, null, null) {
            private final TurnationManager turnM = turnationManager;
            private final Board boarD = board;
            private boolean validThrow=false;

            @Override
            public void activateEffect(Player player) {
                
                turnM.moveByDices().forEach(p->turnM.moveByDices().forEach(g -> {if(g.equals(p)){validThrow=true;}}));
                if (validThrow) {
                    boarD.movePawn(boarD.getPawn(player.getID()), turnM.moveByDices());
                    
                }
            }
        };
    }

    @Override
    public Special parking(Position pos) {
        return new SpecialImpl("parking", pos, Type.SPECIAL, new Effect() {

            @Override
            public void activate(Player palyer) {
                palyer.park();
            }
            
        });
    }

    @Override
    public Special taxes(Position pos) {
        return new SpecialImpl("taxes", pos, Type.SPECIAL, new Effect() {

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
