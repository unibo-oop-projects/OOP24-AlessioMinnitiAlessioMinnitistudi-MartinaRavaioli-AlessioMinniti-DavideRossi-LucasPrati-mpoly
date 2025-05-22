package it.unibo.monopoly.model.turnation.impl;


import it.unibo.monopoly.model.turnation.api.Prisonable;
/**
 * implementation for prisonable quality of player.
 */
public final class PrisonImpl implements Prisonable {

    private static final int PRISON_TURNS = 3;
    private int turns;

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
}
