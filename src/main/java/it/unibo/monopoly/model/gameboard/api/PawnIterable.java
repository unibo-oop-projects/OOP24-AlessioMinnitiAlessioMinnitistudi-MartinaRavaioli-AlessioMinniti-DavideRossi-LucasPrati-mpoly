package it.unibo.monopoly.model.gameboard.api;
/**
 * Iteraple interface for pawn collections.
 * give the iterator to the collection.
 */
public interface PawnIterable {
    /**
     * create the iterator for the pawn collection.
     * @return Iterator
     */
    PawnIterator iterator();
}
