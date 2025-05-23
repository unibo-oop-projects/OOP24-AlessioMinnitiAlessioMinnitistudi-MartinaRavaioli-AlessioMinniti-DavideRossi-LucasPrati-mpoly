package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * this is SpecialFactory interface, defines which special tiles you can create. 
 */
public interface SpecialFactory {

    /**
     * creates the start tiles that has the effect of transfering 200$ to the player that triggered it.
     * @param bank used to make the transaction
     * @return the special tile start
     */
    Special start(Bank bank);

    /**
     * create the go to prison tiles that moves the player on the tile prison.
     * @param pos position of the tile
     * @param board used to move the player pawn
     * @return the special tile goToPrison
     */
    Special goToPrison(Position pos, Board board);

    /**
     * creates the tile prison that keeps the player in stasis for 3 turns unless he gets the same number on two dices.
     * @param pos position of the tile
     * @param board used to move the player pawn
     * @param turnationManager used to get the throw 
     * @return the special tile prison
     */
    Special prison(Position pos);

    /**
     * ccreates the tile parking that keeps the player in stasis for 1 turn.
     * @param pos of the tile 
     * @return the special tile parking
     */
    Special parking(Position pos);

    /**
     * creates the tile taxes that witdraws a definite ammount from the palyer that triggered it's effect.
     * @param pos of the tile
     * @param bank used to make the transaction
     * @return the special tile taxes
     */
    Special taxes(Position pos, Bank bank);


}
