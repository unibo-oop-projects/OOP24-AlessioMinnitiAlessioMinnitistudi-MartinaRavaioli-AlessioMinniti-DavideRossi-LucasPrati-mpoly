package it.unibo.monopoly.model.turnation.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.CircularLinkedList;


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
    public TurnationManagerImpl(final List<Player> plList, final Dice dice) {
        this.players = new CircularLinkedList<>();
        for (final Player p : plList) {
            this.players.addNode(p);
        }
        this.dice = dice;
        this.currPlayer = this.players.getHead();
    }
    /**
     * constructor.
     * @param plList
    */
    @Override
    public void setList(final List<Player> plList) {
        this.players = new CircularLinkedList<>();
        for (final Player p : plList) {
            this.players.addNode(p);
        }
    }
    /**
     * set Dice.
     * @param dice
    */
    @Override
    public final void setDice(final Dice dice) {
        this.dice = dice;
    }
    /**
     * get dice.
     * @return Dice
    */
    @Override
    public Dice getDice() {
        return this.dice;
    }
    /**
     * get player list.
     * @return List of player
    */
    @Override
    public List<Player> getPlayerList() {
        return Collections.unmodifiableList(this.players.toList());
    }
    /**
     * add a player.
     * @param p
    */
    @Override
    public void addPlayer(final Player p) {
        this.players.addNode(p);
    }
    /**
     * set game over.
    */
    @Override
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
    @Override
    public final int getIdCurrPlayer() {
        return this.currPlayer.getID();
    }
    /**
     * return the current player.
     * @return Player
    */
    @Override
    public final Player getCurrPlayer() {
        return this.currPlayer;
    }

}
