 package it.unibo.monopoly.model.gameboard.impl;


import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.EffectFactory;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;


/**
 * a factory for special tiles of the board, each one with it's specific effect.
 */
public final class SpecialFactoryImpl implements SpecialFactory {

    private final EffectFactory factory = new EffectFactoryImpl(); 
    @Override
    public Special start(final Bank bank) {
            final int startAmount = 200;

        return new SpecialImpl("Start", new PositionImpl(0), Group.SPECIAL, factory.depositMoney(startAmount, bank));
    }

    @Override
    public Special goToPrison(final Position pos, final Board board, final TurnationManager turnM) {
        return new SpecialImpl("GoToJail", pos, Group.SPECIAL, factory.putInPrison(board, turnM));
    }

    @Override
    public Special prison(final Position pos) {
        return new SpecialImpl("Jail / Just Visiting", pos, Group.SPECIAL, factory.still());
    }

    @Override
    public Special parking(final Position pos, final TurnationManager turnM) {
        return new SpecialImpl("FreeParking", pos, Group.SPECIAL, factory.park(turnM));
    }

    @Override
    public Special taxes(final Position pos, final Bank bank) {
        final int taxesAmount = 100;
        return new SpecialImpl("Tax", pos, Group.SPECIAL, factory.withdrawMoney(taxesAmount, bank));
    }

    @Override
    public Special chancesAndCommunityChest(final Position pos, final Board board) {
        return new SpecialImpl("Chances and Community chest", pos, Group.SPECIAL, factory.drawChanceAndCommunityChest(board));
    }

}
