package it.unibo.monopoly.model.turnation.api;

import java.util.Collection;

import it.unibo.monopoly.model.gameboard.api.Board;

/**
 * interface of prisonable quality for a player.
 * if added on a player it has the possibility to end up in prison 
 * and be put in stasis for 3 turn unless 
 * he gets the same number on at least 2 dices
 */
public interface Prisonable {

    /**
     * tells whether the player is in prison.
     * @return whether the player is in prison
     */
    boolean isInPrison();

    /**
     * put the player in prison and set the turns he has to wait.
    */
    void putInPrison();

    /**
     * based on the dices throw it tells.
     * if the player is free
     * @param dices to check the throw
     * @param board to move the pawn in case the throw is valid
     * @param player that shal move 
     * @return whether the player can go on with its turn     
     */
    boolean canExit(Collection<Integer> dices, Board board, Player player);

}
