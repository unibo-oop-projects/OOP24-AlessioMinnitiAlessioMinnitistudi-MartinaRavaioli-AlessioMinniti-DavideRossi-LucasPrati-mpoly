package it.unibo.monopoly.model.turnation.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.PlayerIterable;
import it.unibo.monopoly.model.turnation.api.PlayerIterator;
/**
 * player circular list.
 */
public class CircularPlayerList implements PlayerIterable {
    private List<Player> list = new ArrayList<>();

    @Override
    public PlayerIterator iterator() {
        //return new CircularPlayerIteratorImpl(list.toArray(Player[]::new));
        return null;
    }
    
    public void add(final Player p) {
        list.add(p);
    }

    public void remove(final Player p) {
        list.remove(p);
    }

    public boolean contains(final Player p) {
        return list.contains(p);
    }
}
