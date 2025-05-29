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

    /**
     * constructor.
     */
    // public EffectFactoryImpl() { }

    @Override
    public Effect depositMoney(final int amount, final Bank bank) {
        return new Effect() {

            private final String desc = "deposits " + Integer.toString(amount) + "into your bankaccount";

            @Override
            public void activate(final Player player) {
                bank.depositTo(player.getID(), amount);
            }

            @Override
            public String getDescription() {
                return this.desc;
            }
        };
    }

    @Override
    public Effect withdrawMoney(final int amount, final Bank bank) {
        return new Effect() {

            private final String desc = "withdraw " + Integer.toString(amount) + "from your bankaccount";

            @Override
            public void activate(final Player player) {
                bank.withdrawFrom(player.getID(), amount);
            }

            @Override
            public String getDescription() {
                return this.desc;
            }
        };
    }

    @Override
    public Effect putInPrison(final Board board) {


        return new Effect() {

            private static final String DESC = "teleports you onto the prison tile" 
                                            + "\nand put you in stall for 3 turns" 
                                            + "\nunless you get at least 2 matching numbers at the dices";
            @Override
            public void activate(final Player player) {
                player.putInPrison();
                board.movePawnInTile(board.getPawn(player.getID()), "Jail / Just Visiting");
            }

            @Override
            public String getDescription() {
                return DESC;
            }
        };
    }

    @Override
    public Effect park() {

        return new Effect() {

            private static final String DESC = "puts you in stall for the next turn";

            @Override
            public void activate(final Player player) {
                player.park();
            }

            @Override
            public String getDescription() {
                return DESC;
            }
        };
    }

    @Override
    public Effect still() {

        return new Effect() {

            private static final String DESC = "does nothing";

            @Override
            public void activate(final Player player) {
            }

            @Override
            public String getDescription() {
                return DESC;
            }
        };
    }

}
