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
    private boolean isOver;
    private Player currPlayer;
    private Dice dice;
    /**
     * constructor.
     * @param plList
     * @param dice
    */
    public TurnationManagerImpl(final CircularLinkedList<Player> plList, final Dice dice) {
        this.players = new CircularLinkedList<>();
        for (Player p : plList.toList()) {
            this.players.addNode(p);
        }
        this.dice = dice;
    }
    /**
     * constructor.
     * @param plList
    */
    public void setList(final CircularLinkedList<Player> plList) {
        this.players = plList;
    }
    /**
     * set Dice.
     * @param dice
    */
    public final void setDice(final Dice dice) {
        this.dice = dice;
    }
    /**
     * get dice.
     * @return Dice
    */
    public Dice getDice() {
        return this.dice;
    }
    /**
     * get player list.
     * @return Circular List of player
    */
    public CircularLinkedList<Player> getPlayerList() {
        return this.players;
    }
    /**
     * add a player.
     * @param p
    */
    public void addPlayer(final Player p) {
        this.players.addNode(p);
    }
    /**
     * set game over.
    */
    public void setOver() {
        this.isOver = true;
    }
    /**
     * check if is over.
     * @return boolean
    */
    @Override
    public final boolean isOver() { 
        return this.isOver;
    }
    /**
     * get the next player.
     * @return player
    */
    @Override
    public final Player getNextPlayer() { 
        this.currPlayer = players.giveNextNode(this.currPlayer);
        return this.currPlayer;
    }
    /**
     * throw the dices.
     * @return Collection of Integer
    */
    @Override
    public final Collection<Integer> moveByDices() { 
        return this.dice.throwDices();
    }
    /**
     * return the id of the current player.
     * @return int
    */
    public final int getIdCurrPlayer() {
        return this.currPlayer.getID();
    }
}
