package it.unibo.monopoly.model.gameboard.impl;


import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.EffectFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * implementation for EffectFactory.
 */
public final class EffectFactoryImpl implements EffectFactory {

    @Override
    public Effect depositMoney(final int amount, final Bank bank) {
        return new Effect() {

            @Override
            public void activate(final Player player) {
                bank.depositTo(player.toString(), amount);
            }
            
        };
    }

    @Override
    public Effect withdrawMoney(final int amount, final Bank bank) {
        return new Effect() {

            @Override
            public void activate(final Player player) {
                bank.withdrawFrom(player.toString(), amount);
            }
            
        };
    }

    @Override
    public Effect putInPrison(final Board board) {
        return new Effect() {

            @Override
            public void activate(final Player player) {
                player.putInPrison();
                //board.movePawn(board.getPawn(player.getID()), Set.of(1 - pos.getPos()));
            }
        };
    }

    @Override
    public Effect park() {
        return new Effect() {

            @Override
            public void activate(final Player player) {
                player.park();
            }
        };
    }

    @Override
    public Effect still() {
        return new Effect() {

            @Override
            public void activate(final Player player) {
            }
            
        };
    }

}
