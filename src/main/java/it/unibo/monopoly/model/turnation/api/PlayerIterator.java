package it.unibo.monopoly.model.turnation.api;

/**
 * iterator of players.
 */
public interface PlayerIterator {
    /**
     * get you the next player in the iterator. 
     * @return Player
     */
    Player getNext();
    /**
     * tells if the iterator contains other players.
     * @return bool
     */
    boolean hasMore();
    /**
     * get the current player.
     * @return Player
     */
    Player getCurrent();
}
