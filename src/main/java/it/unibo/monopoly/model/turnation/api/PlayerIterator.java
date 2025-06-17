package it.unibo.monopoly.model.turnation.api;

/**
 * iterator of players.
 */
public interface PlayerIterator {
    /**
     * get you the next player in the iterator. 
     * @return Pawn
     */
    Player getNext();
    /**
     * tells if the iterator contains other pawns.
     * @return bool
     */
    boolean hasMore();
}
