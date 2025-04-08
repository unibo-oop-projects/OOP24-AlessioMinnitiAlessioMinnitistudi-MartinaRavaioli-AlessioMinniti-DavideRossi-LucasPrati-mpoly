package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * player implementation.
*/
public abstract class PlayerImpl implements Player {
    private int id;

    public PlayerImpl(int id){
        this.id=id;
    }
}
