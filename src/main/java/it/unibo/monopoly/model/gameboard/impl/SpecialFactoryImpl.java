package it.unibo.monopoly.model.gameboard.impl;


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


    public SpecialFactoryImpl() {
    }

    @Override
    public Special start(final Bank bank) {

        return new SpecialImpl("Start", new PositionImpl(0), Type.SPECIAL, new Effect() {

            private final static int START_AMOUNT = 200;

            //DA CAMBIARE CON IL METODO PER AVERE IL NOME CHE ORA NON C'E'
            @Override
            public void activate(Player palyer) {
                bank.depositTo(palyer.toString(), START_AMOUNT);
            }
            
        });
    }

    @Override
    public Special goToPrison(Position pos, final Board board) {
        return new SpecialImpl("GoToPrison", pos, Type.SPECIAL, new Effect() {

            //TODO fallo con la differenza !!
            //o con il metodo da chiedere ad ale 

            @Override
            public void activate(Player palyer) {
                palyer.putInPrison();
                board.movePawn( board.getPawn(palyer.getID()), Set.of(1 - pos.getPos()) );
            }
            
        });
    }

    @Override
    public Special prison(Position pos, final Board board, final TurnationManager turnationManager) {
        return new SpecialImpl(null, pos, null, null) {
            private boolean validThrow=false;

            @Override
            public void activateEffect(Player player) {
                
                turnationManager.moveByDices().forEach(p->turnationManager.moveByDices().forEach(g -> {if(g.equals(p)){validThrow=true;}}));
                if (validThrow) {
                    board.movePawn(board.getPawn(player.getID()), turnationManager.moveByDices());
                    
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
    public Special taxes(Position pos, final Bank bank) {
        return new SpecialImpl("taxes", pos, Type.SPECIAL, new Effect() {

            private final static int TAXES_AMOUNT = 100;

            //DA CAMBIARE CON IL METODO PER AVERE IL NOME CHE ORA NON C'E'
            @Override
            public void activate(Player palyer) {
                bank.withdrawFrom(palyer.toString(), TAXES_AMOUNT);
            }
            
        });
    }

    public Special bho( final Bank bank){
        return new SpecialImpl(null, null, null, p -> {bank.withdrawFrom(p.toString(), 10);});
    }

}
