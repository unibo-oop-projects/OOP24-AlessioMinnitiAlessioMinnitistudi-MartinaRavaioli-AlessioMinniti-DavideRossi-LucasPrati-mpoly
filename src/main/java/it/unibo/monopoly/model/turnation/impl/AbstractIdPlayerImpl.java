package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.resources.Identifiable;

/**
 * player implementation.
*/
public abstract class AbstractIdPlayerImpl implements Player,Identifiable<Integer> {
    private int id;

    public AbstractIdPlayerImpl(int id){
        this.id=id;
    }

    @Override
    public Integer getID(){
        return this.id;
    }

    @Override
    public void setID(Integer value){
        this.id=value;
    }
}
