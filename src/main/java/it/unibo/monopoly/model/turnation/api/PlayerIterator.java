package it.unibo.monopoly.model.turnation.api;

import java.util.Iterator;
import java.util.List;

/**
 * iterator of players.
 */
public interface PlayerIterator extends Iterator<Player>{
    /**
     * get the current player.
     * @return Player
     */
    Player getCurrent();
    /**
     * add a new player for the iterator.
     * @param p player
     */
    void add(Player p);
    /**
     * remove a player.
     * @param p player
    */
    void remove(Player p);
    /**
     * remove all the player from the iterator.
     */
    void clear();
    /**
     * transform the iterator in a unmodifiable list.
     * @return List of players
     */
    List<Player> toList();
    /**
     * check if the iterator contains a player.
     * @param p player
     * @return
     */
    boolean contains(Player p);
}
