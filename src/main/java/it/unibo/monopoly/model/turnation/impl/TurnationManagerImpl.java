package it.unibo.monopoly.model.turnation.impl;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.resources.CircularLinkedList;


/**
 * turnation manager implementation.
*/
public class TurnationManagerImpl implements TurnationManager {
    private CircularLinkedList<Player> players;
    private boolean isOver=false;

    public TurnationManagerImpl(CircularLinkedList<Player> plList){
        this.players=plList;
    }

    public void setList(CircularLinkedList<Player> plList){
        this.players=plList;
    }

    public CircularLinkedList<Player> getList(){
        return this.players;
    }

    public void addPlayer(Player p){
        this.players.addNode(p);
    }

    public void setOver(){
        this.isOver=true;
    }

    @Override
    public final boolean isOver() { 
        return this.isOver;
    }

    @Override
    public final Player getNextPlayer() { 
        return players.
    }

    @Override
    public final Pair<Integer, Integer> moveByDices() { 
        return null; 
    }
}
