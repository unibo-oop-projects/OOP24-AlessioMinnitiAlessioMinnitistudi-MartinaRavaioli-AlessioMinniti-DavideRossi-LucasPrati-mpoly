package it.unibo.monopoly.model.turnation.impl;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * turnation manager implementation.
*/
public class TurnationManagerImpl implements TurnationManager {
    private List<Player> players;

    public TurnationManagerImpl(List<Player> plList){
        this.players=plList;
    }

    public void setList(List<Player> plList){
        this.players=plList;
    }

    public List<Player> getList(){
        return this.players;
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    @Override
    public final boolean isOver() { 
        return false; 
    }
    @Override
    public final Player getNextPlayer() { 
        return null; 
    }
    @Override
    public final Pair<Integer, Integer> moveByDices() { 
        return null; 
    }
}
