 package it.unibo.monopoly.model.gameboard.impl;


import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.EffectFactory;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;


/**
 * a factory for special tiles of the board, each one with it's specific effect.
 */
public final class SpecialFactoryImpl implements SpecialFactory {

    private final EffectFactory factory = new EffectFactoryImpl(); 
    @Override
    public Special start(final String name, final Bank bank) {
            final int startAmount = 200;

        return new SpecialImpl(name, new PositionImpl(0), Group.SPECIAL, factory.depositMoney(startAmount, bank));
    }

    @Override
    public Special goToPrison(final String name, final Position pos, final Board board) {
        return new SpecialImpl(name, pos, Group.SPECIAL, factory.putInPrison(board));
    }

    @Override
    public Special prison(final String name, final Position pos) {
        return new SpecialImpl(name, pos, null, factory.still());
    }

    @Override
    public Special parking(final String name, final Position pos) {
        return new SpecialImpl(name, pos, Group.SPECIAL, factory.park());
    }

    @Override
    public Special taxes(final String name, final Position pos, final Bank bank) {
        final int taxesAmount = 100;
        return new SpecialImpl(name, pos, Group.SPECIAL, factory.withdrawMoney(taxesAmount, bank));
    }

}
