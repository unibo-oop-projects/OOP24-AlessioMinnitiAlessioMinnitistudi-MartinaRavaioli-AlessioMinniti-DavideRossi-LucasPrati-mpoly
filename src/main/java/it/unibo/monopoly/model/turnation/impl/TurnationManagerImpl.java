package it.unibo.monopoly.model.turnation.impl;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.transactions.api.BankState;
import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.impl.CircularLinkedList;


/**
 * turnation manager implementation.
*/
public class TurnationManagerImpl implements TurnationManager {
    private CircularLinkedList<Player> players;
    private boolean isOver;
    private Player currPlayer;
    private Dice dice;
    private BankState bankState;
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
        this.currPlayer = plList.get(0);
    }
    /**
     * constructor.
     * @param plList
     * @param dice
     * @param bankState
    */
    public TurnationManagerImpl(final List<Player> plList, final Dice dice, final BankState bankState) {
        this.bankState = bankState;
        this.players = new CircularLinkedList<>();
        for (final Player p : plList) {
            this.players.addNode(p);
        }
        this.dice = dice;
        this.currPlayer = plList.get(0);
    }
    /**
     * set list.
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
        return PlayerImpl.of(this.currPlayer.getID(), this.currPlayer.getName(), this.currPlayer.getColor());
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
        return PlayerImpl.of(this.currPlayer.getID(), this.currPlayer.getName(), this.currPlayer.getColor());
    }

    @Override
    public final boolean isCurrentPlayerInPrison() {
        return this.currPlayer.isInPrison();
    }

    @Override
    public final boolean canExitPrison(final Collection<Integer> value, final Board board) {
        return this.currPlayer.canExitPrison(value, board);
    }

    @Override
    public final boolean canThrowDices() {
        return true;
    }

    @Override
    public final boolean canPassTurn() {
        return this.bankState.allMandatoryTransactionsCompleted();
    }

    @Override
    public final boolean playerDiesIfTurnPassed() {
        return this.bankState.canContinuePlay(this.currPlayer);
    }

    @Override
    public final Map.Entry<String, Integer> getWinner() {
        final Map.Entry<String, Integer> winner = Map.entry("", 0);
        for (final Map.Entry<String, Integer> p : getRanking().entrySet()) {
            if (p.getValue() > winner.getValue()) {
                winner.setValue(p.getValue());
            }
        }

        return winner;
    }

    @Override
    public final Map<String, Integer> getRanking() {
        return this.bankState.rankPlayers();
    }

    @Override
    public final void deletePlayer(final Player player) {
        this.players.deleteNode(player);
    }

}
