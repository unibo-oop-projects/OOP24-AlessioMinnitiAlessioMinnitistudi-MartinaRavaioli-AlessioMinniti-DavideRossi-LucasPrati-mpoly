package it.unibo.monopoly.model.turnation.impl;


import java.awt.Color;
import java.util.Collection;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Prisonable;
/**
 * implementation for prisonable quality of player.
 */
public final class PrisonablePlayer implements Prisonable, Player {

    private static final int PRISON_TURNS = 3;
    private int turns;
    //TODO decidi se tenere il metodo con il for (io voto per il no poi vedi te me del futuro)
    //private boolean validThrow;
    private final Player player;

    /**
     * constructor for ParkablePlayer.
     * @param player the base player.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
        justification = "must keep reference to the object, not a copy in order to use decorator pattern")
    public PrisonablePlayer(final Player player) {
        this.player = player;
    }

    @Override
    public boolean isInPrison() {
        return turns > 0;
    }

    @Override
    public void putInPrison() {
        turns = PRISON_TURNS;
    }

    @Override
    public boolean canExitPrison(final Collection<Integer> dices) {
        final List<Integer> l = List.copyOf(dices);
        final List<Integer> l1 = l.stream().distinct().toList();
        /*for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.size(); j++) {
                if (i != j && l.get(i).equals(l.get(j))) {
                    validThrow = true; 
                }
            }
        }*/

        if (l.size() != l1.size()) {
            this.turns = 0;
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

    @Override
    public int turnLeftInPrison() {
        return this.turns;
    }

    @Override
    public void decreaseTurnsInPrison() {
        turns = turns - 1;
    }

    @Override
    public void passTurn() {
        this.player.passTurn();
    }
}
