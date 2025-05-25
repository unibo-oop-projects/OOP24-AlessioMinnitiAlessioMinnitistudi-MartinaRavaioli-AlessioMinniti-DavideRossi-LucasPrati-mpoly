 package it.unibo.monopoly.model.gameboard.impl;


import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.EffectFactory;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.SpecialFactory;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
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
    public Special goToPrison(final Position pos, final Board board) {
        /*return new Special() {

            private Group group = Group.SPECIAL;


        public setBoard(Board board)

            @Override
            public void setGroup(Group group) {
                this.group = group;
            }

            @Override
            public Position getPosition() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
            }

            @Override
            public String getName() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getName'");
            }

            @Override
            public void activateEffect(Player player) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activateEffect'");
            }

            @Override
            public Effect getEffect() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getEffect'");
            }

            @Override
            public Group getGroup() {
                return this .
            }
            
        }; */
        return new SpecialImpl("GoToPrison", pos, Group.SPECIAL, factory.putInPrison(board));
    }

    @Override
    public Special prison(final Position pos) {
        return new SpecialImpl("prison", pos, null, factory.still());
    }

    @Override
    public Special parking(final Position pos) {
        return new SpecialImpl("parking", pos, Group.SPECIAL, factory.park());
    }

    @Override
    public Special taxes(final Position pos, final Bank bank) {
        final int taxesAmount = 100;
        return new SpecialImpl("taxes", pos, Group.SPECIAL, factory.withdrawMoney(taxesAmount, bank));
    }

    /**
     * metodo fatto ab cazzum.
     * @param bank banca
     * @return bho
     */
    public Special bho(final Bank bank) {
        return new SpecialImpl(null, null, null, p -> { 
                                    bank.withdrawFrom(p.toString(), 10); });
    }

}
