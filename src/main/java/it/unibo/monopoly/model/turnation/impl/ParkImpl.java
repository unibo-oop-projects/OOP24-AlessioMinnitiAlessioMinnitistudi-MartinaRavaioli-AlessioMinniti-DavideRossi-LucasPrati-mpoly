package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Parkable;

/**
 * implementation for parkable quality of player.
 */
public final class ParkImpl implements Parkable {

    private boolean in;

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
}
