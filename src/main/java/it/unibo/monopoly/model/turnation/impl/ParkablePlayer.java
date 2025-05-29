package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;
import java.util.Collection;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.turnation.api.Parkable;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * implementation for parkable quality of player.
 */
public final class ParkablePlayer implements Parkable, Player {

    private boolean in;
    private final Player pl;

    /**
     * constructor for Parkable player.
     * @param player the base player 
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
        justification = "must keep reference to the object, not a copy")
    public ParkablePlayer(final Player player) {
        this.pl = player;
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
        return pl.getID();
    }

    @Override
    public String getName() {
        return pl.getName();
    }

    @Override
    public Color getColor() {
        return pl.getColor();
    }

    @Override
    public boolean isAlive() {
        return pl.isAlive();
    }

    @Override
    public boolean isInPrison() {
        return pl.isInPrison();
    }

    @Override
    public void putInPrison() {
        pl.putInPrison();
    }

    @Override
    public boolean canExitPrison(final Collection<Integer> dices) {
        return pl.canExitPrison(dices);
    }
}
