package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;
import java.util.Collection;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.Parkable;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * implementation for parkable quality of player.
 */
public final class ParkablePlayer implements Parkable, Player {

    private boolean in;
    private final Player player;

    /**
     * constructor for Parkable player.
     * @param player the base player 
     */
    public ParkablePlayer(final Player player) {
        this.player = player;
    }

    @Override
    public boolean isParked() {
        if (in) {
            in = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void park() {
        in = true;
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
    public boolean isInPrison() {
        return player.isInPrison();
    }

    @Override
    public void putInPrison() {
        player.putInPrison();
    }

    @Override
    public boolean canExitPrison(final Collection<Integer> dices, final Board board, final Player player) {
        return player.canExitPrison(dices, board, player);
    }
}
