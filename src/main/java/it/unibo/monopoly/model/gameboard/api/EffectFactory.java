package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.transactions.api.Bank;

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
     * @return the effect
     */
    Effect putInPrison(Board board);

    /**
     * an effect that park the player.
     * @return the effect
     */
    Effect park();

    /**
     * an effect that does nothing.
     * @return the effect
     */
    Effect still();
}
