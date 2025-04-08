package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.resources.Identifiable;

/**
 * player implementation.
*/
public abstract class PlayerImpl implements Player,Identifiable<Integer> {
    private int id;

    public PlayerImpl(int id){
        this.id=id;
    }

    public Integer getID(){
        return this.id;
    }

    public void setID(int value){
        this.id=value;
    }
}
