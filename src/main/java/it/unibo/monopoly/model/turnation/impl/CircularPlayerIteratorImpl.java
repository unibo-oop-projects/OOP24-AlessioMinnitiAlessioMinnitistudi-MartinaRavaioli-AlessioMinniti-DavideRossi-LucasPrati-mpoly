package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.PlayerIterator;
/**
 * circular iterator for the players.
 */
public class CircularPlayerIteratorImpl implements PlayerIterator {
    private Player currPlayer;
    private int currPosition;
    private final List<Player> elems;
    /**
     * constructor.
     * @param list list of players
     */
    public CircularPlayerIteratorImpl(final List<Player> list) {
        this.elems = new ArrayList<>(list);
        this.currPosition = 0;
        this.currPlayer = this.elems.get(0);
    }

    @Override
    public final Player next() {
        if (hasNext()) {
            this.currPosition = (this.currPosition + 1) % this.elems.size();
            this.currPlayer = this.elems.get(this.currPosition);
            return createCurrPlayerCopy();
        }
        return null;
    }
    /**
     * create a copy of the player.
     * @return Player
     */
    private Player createCurrPlayerCopy() {
        return new Player() {

            @Override
            public Integer getID() {
               return currPlayer.getID();
            }

            @Override
            public String getName() {
                return currPlayer.getName();
            }

            @Override
            public Color getColor() {
                return currPlayer.getColor();
            }

            @Override
            public boolean isAlive() {
                return currPlayer.isAlive();
            }

            @Override
            public boolean isParked() {
                return currPlayer.isParked();
            }

            @Override
            public void park() {
                currPlayer.park();
            }

            @Override
            public boolean isInPrison() {
                return currPlayer.isInPrison();
            }

            @Override
            public void putInPrison() {
                currPlayer.putInPrison();
            }

            @Override
            public boolean canExitPrison(final Collection<Integer> dice) {
               return currPlayer.canExitPrison(dice);
            }

            @Override
            public int turnLeftInPrison() {
                return currPlayer.turnLeftInPrison();
            }

            @Override
            public void decreaseTurnsInPrison() {
                currPlayer.decreaseTurnsInPrison();
            }

            @Override
            public void passTurn() {
                currPlayer.passTurn();
            }

        };
    }

    @Override
    public final boolean hasNext() {
        return !this.elems.isEmpty();
    }
    /**
     * add the player.
     * @param p player
     */
    @Override
    public void add(final Player p) {
        this.elems.add(p);
    }
    /**
    * remove the player.
    * @param p player
    */
    @Override
    public void remove(final Player p) {
        final int id = p.getID();
        if (this.currPlayer.getID().equals(id)) {
            next();
        }
        this.elems.removeIf(player -> player.getID().equals(id));
    }
    /**
     * return true if the list contains the player, false if not.
     * @param p player
     * @return bool
     */
    @Override
    public boolean contains(final Player p) {
        return this.elems.contains(p);
    }
    /**
     * convert the circular iterator into a list.
     * @return List of Players
     */
    @Override
    public List<Player> toList() {
        return Collections.unmodifiableList(this.elems);
    }

    @Override
    public final Player getCurrent() {
        return createCurrPlayerCopy();
    }
    /**
     * clear the iterator.
     */
    @Override
    public void clear() {
        this.elems.clear();
        this.currPlayer = null;
        this.currPosition = 0;
    }

    @Override
    public final void initializeCurrPlayer(final int id) {
        for (final Player p : this.elems) {
            if (p.getID() == id) {
                this.currPlayer = p;
                this.currPosition = this.elems.indexOf(this.currPlayer);
            }
        }
    }
}
