package it.unibo.monopoly.model.turnation.impl;

import java.util.Optional;

import it.unibo.monopoly.model.turnation.api.Parkable;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.Prisonable;

/**
 * player implementation.
*/
public class PlayerImpl implements Player{

    Optional<Prisonable> prison;
    Optional<Parkable> parking;
    

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public void makeMove(int steps) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeMove'");
    }

    @Override
    public boolean isAlive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
    }

    @Override
    public boolean isParked() {
        if (parking.isPresent()) {
            return parking.get().isParked();
        }else{
            return false;
        }
    }

    @Override
    public void park() {
        if (parking.isPresent()) {
            parking.get().park();
        }
    }

    @Override
    public boolean isInPrison() {
        if (prison.isPresent()) {
            return prison.get().isInPrison();
        }else{
            return false;
        }
    }

    @Override
    public void putInPrison() {
        if (prison.isPresent()) {
            prison.get().putInPrison();
        }
    }
   
}
