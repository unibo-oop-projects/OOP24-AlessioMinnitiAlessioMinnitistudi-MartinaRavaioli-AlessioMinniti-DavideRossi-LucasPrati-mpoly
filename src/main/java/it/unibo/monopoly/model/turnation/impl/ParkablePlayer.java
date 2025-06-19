package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;
import java.util.Collection;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.turnation.api.Parkable;
import it.unibo.monopoly.model.turnation.api.Player;

/**
 * implementation for parkable quality of player.
 */
public final class ParkablePlayer implements Parkable, Player {

    private static final int PARK_TURNS = 2;
    private int in;
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
        return in > 0;
    }

    @Override
    public void park() {
        in = PARK_TURNS;
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

    @Override
    public int turnLeftInPrison() {
        return pl.turnLeftInPrison();
    }

    @Override
    public void decreaseTurnsInPrison() {
        pl.decreaseTurnsInPrison();
    }

    @Override
    public void passTurn() {
        this.in = in - 1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.in;
        hash = 73 * hash + Objects.hashCode(this.pl);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParkablePlayer other = (ParkablePlayer) obj;
        if (this.in != other.in) {
            return false;
        }
        return Objects.equals(this.pl, other.pl);
    }
}
