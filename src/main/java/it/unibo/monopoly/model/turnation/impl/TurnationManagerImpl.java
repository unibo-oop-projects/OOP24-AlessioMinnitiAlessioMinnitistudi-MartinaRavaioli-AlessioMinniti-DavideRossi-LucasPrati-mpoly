package it.unibo.monopoly.model.turnation.impl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    private CircularLinkedList<Player> players; /**list of players. */
    private boolean isOver; /**is Over bool. */
    private Player currPlayer; /**current player. */
    private Dice dice; /**dice. */
    private BankState bankState; /**bankState to communicate with the bank. */
    /**
     * constructor.
     * @param plList list of players
     * @param dice dice
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
     * @param plList list of players
     * @param dice dice
     * @param bankState bankState to communicate with the bank
    */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "Injection of shared mutable dependencies is intentional and controlled in this architecture."
    )
    public TurnationManagerImpl(final List<Player> plList, final Dice dice, final BankState bankState) {
        this.bankState = bankState;
        this.players = new CircularLinkedList<>();
        for (final Player p : plList) {
            this.players.addNode(p);
        }
        this.dice = dice;
        this.currPlayer = plList.get(0);
    }

    @Override
    public final void setList(final List<Player> plList) {
        this.players = new CircularLinkedList<>();
        for (final Player p : plList) {
            this.players.addNode(p);
        }
    }

    @Override
    public final void setDice(final Dice dice) {
        this.dice = dice;
    }

    @Override
    public final Dice getDice() {
        return this.dice;
    }

    @Override
    public final List<Player> getPlayerList() {
        return Collections.unmodifiableList(this.players.toList());
    }

    @Override
    public final void addPlayer(final Player p) {
        this.players.addNode(p);
    }

    @Override
    public final void setOver() {
        this.isOver = true;
    }

    @Override
    public final boolean isOver() { 
        return this.isOver;
    }

    @Override
    public final Player getNextPlayer() { 
        this.currPlayer = players.giveNextNode(this.currPlayer);
        return PlayerImpl.of(this.currPlayer.getID(), this.currPlayer.getName(), this.currPlayer.getColor());
    }

    @Override
    public final Collection<Integer> moveByDices() { 
        return this.dice.throwDices();
    }

    @Override
    public final int getIdCurrPlayer() {
        return this.currPlayer.getID();
    }

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
        return this.currPlayer.canExitPrison(value);
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
    public final Pair<String, Integer> getWinner() {
        final Pair<Integer, Integer> winner = this.bankState.rankPlayers().get(0);
        final Pair<String, Integer> winnerName;

        for (final Pair<Integer, Integer> p : this.bankState.rankPlayers()) {
            if (p.getRight() > winner.getRight()) {
                winner.setValue(p.getRight());
            }
        }

        winnerName = Pair.of(this.players.toList().get(winner.getLeft()).getName(), winner.getRight());
        return winnerName;
    }

    @Override
    public final List<Pair<String, Integer>> getRanking() {
        final List<Pair<String, Integer>> list = new ArrayList<>();
        for (final Pair<Integer, Integer> p : this.bankState.rankPlayers()) {
            list.add(Pair.of(this.players.toList().get(p.getLeft()).getName(), p.getRight()));
        }

        return list;
    }

    @Override
    public final void deletePlayer(final Player player) {
        this.players.deleteNode(player);
    }

}
