package it.unibo.monopoly.model.turnation.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.PlayerIterator;
/**
 * circular iterator for the players.
 */
public class CircularPlayerIteratorImpl implements PlayerIterator {
    private Player currPlayer;
    private int currPosition;
    private List<Player> elems = new ArrayList<>();

    public CircularPlayerIteratorImpl(final List<Player> list) {
        this.elems = list;
        this.currPosition = 0;
        this.currPlayer = this.elems.get(0);
    }

    @Override
    public Player getNext() {
        if (hasMore()) {
            Player result = this.currPlayer;
            this.currPosition = this.currPosition++ % this.elems.size();
            this.currPlayer = this.elems.get(this.currPosition);
            return result;
        }
        return null;
    }

    @Override
    public boolean hasMore() {
        return !this.elems.isEmpty();
    }

    public void add(final Player p) {
        this.elems.add(p);
    }

    public void remove(final Player p) {
        this.elems.remove(p);
    }

    public boolean contains(final Player p) {
        return this.elems.contains(p);
    }
}
