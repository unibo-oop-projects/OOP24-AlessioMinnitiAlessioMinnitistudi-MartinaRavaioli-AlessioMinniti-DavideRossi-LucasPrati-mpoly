package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Parkable;

public class ParkImpl implements Parkable{

    private boolean in = false;

    @Override
    public boolean isParked() {
        if (in) {
            in = false;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void park() {
        in = true;
    }
}
