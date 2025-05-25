package it.unibo.monopoly.model.turnation.impl;


import java.awt.Color;
import java.util.Collection;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
        justification = "must keep reference to the object, not a copy")
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
    public boolean canExitPrison(final Collection<Integer> dices, final Board board) {
        final List<Integer> l = List.copyOf(dices);
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.size(); j++) {
                if (i != j && l.get(i).equals(l.get(j))) {
                    validThrow = true; 
                }
            }
        }

        if (validThrow) {
            this.turns = 0;
            board.movePawn(board.getPawn(this.getID()), dices);
            validThrow = false;
            return true;
        }
        return false;
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
}
