package it.unibo.monopoly.model.turnation.impl;


import java.awt.Color;
import java.util.Collection;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Prisonable;
/**
 * implementation for prisonable quality of player.
 */
public final class PrisonablePlayer implements Prisonable, Player {

    private static final int PRISON_TURNS = 3;
    private int turns;
    private boolean validThrow;
    private final Player player;

    /**
     * constructor for ParkablePlayer.
     * @param player the base player.
     */
    public PrisonablePlayer(final Player player) {
        this.player = player;
    }

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
    public boolean canExit(final Collection<Integer> dices, final Board board, final Player player) {
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

    
    @Override
    public Integer getID() {
        return player.getID();
    }

    @Override
    public String getName() {
        return player.getName();    
    }

    @Override
    public Color getColor() {
        return player.getColor();
    }

    @Override
    public boolean isAlive() {
        return player.isAlive();
    }

    @Override
    public boolean isParked() {
        return player.isParked();
    }

    @Override
    public void park() {
        player.park();
    }

    @Override
    public boolean canExitPrison(final Collection<Integer> dices, final Board board, final Player player) {
        return player.canExitPrison(dices, board, player);
    }
}
