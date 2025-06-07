package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * a factory for effects that can be used.
 * for special, unexpected and probability tiles
 */
public interface EffectFactory {

    /**
     * an effect that deposit an ammount.
     * of money in the player bank account
     * @param amount tha needs to be deposited
     * @param bank that make the transaction
     * @return the effect
     */
    Effect depositMoney(int amount, Bank bank);

    /**
     * an effect that withdraw an ammount.
     * of money from the player bank account
     * @param amount tha needs to be withdrawn
     * @param bank that make the transaction
     * @return the effect
     */
    Effect withdrawMoney(int amount, Bank bank);

    /**
     * an effect that sends to prison the player.
     * @param board to tip the players pawn to the prison tile
     * @param turnM used to put the player in prison
     * @return the effect
     */
    Effect putInPrison(Board board, TurnationManager turnM);

    /**
     * an effect that park the player.
     * @param turnM used to park the player
     * @return the effect
     */
    Effect park(TurnationManager turnM);

    /**
     * draws a chanche and community chest card and then acivate its effect.
     * @param board to draw the card
     * @return the effect
     */
    Effect drawChancesAndCommunity(Board board);

    /**
     * an effect that does nothing.
     * @return the effect
     */
    Effect still();
}
