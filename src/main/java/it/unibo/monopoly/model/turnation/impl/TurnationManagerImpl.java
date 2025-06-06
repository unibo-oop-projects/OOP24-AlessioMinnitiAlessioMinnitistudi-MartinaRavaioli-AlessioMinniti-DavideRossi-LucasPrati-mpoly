package it.unibo.monopoly.model.turnation.impl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    private Player currPlayer; /**current player. */
    private Dice dice; /**dice. */
    private BankState bankState; /**bankState to communicate with the bank. */
    private boolean diceThrown; /**tells if the current player has already thrown the dices. */
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
        this.diceThrown = false;
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
    public final boolean isOver() { 
        return this.players.toList().size() < 2;
    }

    @Override
    public final Player getNextPlayer() { 
        this.currPlayer = players.giveNextNode(this.currPlayer);
        this.diceThrown = false;
        return PlayerImpl.of(this.currPlayer.getID(), this.currPlayer.getName(), this.currPlayer.getColor());
    }

    @Override
    public final Collection<Integer> moveByDices() throws IllegalAccessException { 
        if (!hasCurrPlayerThrownDices()) {
            this.diceThrown = true;
            return this.dice.throwDices();
        } else {
            throw new IllegalAccessException("the current player has already thrown the dices");
        }
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
    public final boolean canExitPrison(final Collection<Integer> value) {
        return this.currPlayer.canExitPrison(value);
    }

    @Override
    public final boolean canPassTurn() {
        return this.bankState.allMandatoryTransactionsCompleted() && hasCurrPlayerThrownDices();
    }

    @Override
    public final boolean playerDiesIfTurnPassed() {
        return !this.bankState.canContinuePlay(this.currPlayer);
    }

    @Override
    public final Pair<String, Integer> getWinner() {
        Pair<Integer, Integer> winner = Pair.of(this.bankState.rankPlayers().get(0).getLeft(), this.bankState.rankPlayers().get(0).getRight());
        final Pair<String, Integer> winnerName;
        Player player = this.players.getHead();

        for (final Pair<Integer, Integer> p : this.bankState.rankPlayers()) {
            if (p.getRight() > winner.getRight()) {
                winner = Pair.of(p.getLeft(), p.getRight());
            }
        }

        for (final Player pl : this.players.toList()) {
            if (pl.getID().equals(winner.getLeft())) {
                player = pl;
            }
        }
        winnerName = Pair.of(player.getName(), winner.getRight());
        return winnerName;
    }

    @Override
    public final List<Pair<String, Integer>> getRanking() {
        final List<Pair<String, Integer>> list = new ArrayList<>();
        for (final Pair<Integer, Integer> p : this.bankState.rankPlayers()) {

            for (final Player pl : this.players.toList()) {
                if (pl.getID().equals(p.getLeft())) {
                    list.add(Pair.of(pl.getName(), p.getRight()));
                }
            }
        }

        return list;
    }

    @Override
    public final void deletePlayer(final Player player) {
        final List<Player> list = this.players.toList();

        for (final Player p : list) {
            if (p.getID().equals(player.getID())) {
                list.remove(p);
            }
        }

        getNextPlayer();
        this.players.clear();

        for (final Player p : list) {
            this.players.addNode(p);
        }
        this.bankState.deletePlayer(player);
    }
    @Override
    public final void resetBankState() {
        this.bankState.resetTransactionData();
    }
    @Override
    public final boolean hasCurrPlayerThrownDices() {
        return this.diceThrown;
    }
    @Override
    public final boolean isCurrentPlayerParked() {
        return this.currPlayer.isParked();
    }
    @Override
    public final int currentPlayerTurnsLeftInPrison() {
        return this.currPlayer.turnLeftInPrison();
    }
    @Override
    public final void decreaseTurnsInPrison() {
        this.currPlayer.decreaseTurnsInPrison();
    }
    @Override
    public final void passedParkTurn() {
        this.currPlayer.passTurn();
    }
    @Override
    public final void putCurrentPlayerInPrison() {
        this.currPlayer.putInPrison();
    }
    @Override
    public final void parkCurrentPlayer() {
        this.currPlayer.park();
    }

}
