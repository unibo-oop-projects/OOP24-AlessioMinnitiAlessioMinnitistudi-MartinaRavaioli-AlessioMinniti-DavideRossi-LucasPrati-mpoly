package it.unibo.monopoly.model.turnation.impl;


import java.util.Collection;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Prisonable;
/**
 * implementation for prisonable quality of player.
 */
public final class PrisonImpl implements Prisonable {

    private static final int PRISON_TURNS = 3;
    private int turns;
    private boolean validThrow;

    @Override
    public boolean isInPrison() {
        if (turns > 0) {
            turns -= 1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void putInPrison() {
        turns = PRISON_TURNS;
    }

    @Override
    public boolean canExit(Collection<Integer> dices, Board board, Player player) {
        dices.forEach(p -> dices.forEach(g -> { 
                                                if (g.equals(p)) { 
                                                    validThrow = true; 
                                                }
                                            }
                                        ));
        if (validThrow) {
            board.movePawn(board.getPawn(player.getID()), dices);
        }
        return validThrow;
    }
}
