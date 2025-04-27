package it.unibo.monopoly.model.turnation.impl;

import java.util.Collection;

import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.resources.CircularLinkedList;


/**
 * turnation manager implementation.
*/
public class TurnationManagerImpl implements TurnationManager {
    private CircularLinkedList<Player> players;
    private boolean isOver=false;
    private Player currPlayer;
    private final Dice dice;

    public TurnationManagerImpl(CircularLinkedList<Player> plList, Dice dice){
        this.players=plList;
        this.dice = dice;
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
        currPlayer = players.giveNextNode(currPlayer);
        return currPlayer;
    }

    @Override
    public final Collection<Integer> moveByDices() { 
        return dice.throwDices();
    }
}
