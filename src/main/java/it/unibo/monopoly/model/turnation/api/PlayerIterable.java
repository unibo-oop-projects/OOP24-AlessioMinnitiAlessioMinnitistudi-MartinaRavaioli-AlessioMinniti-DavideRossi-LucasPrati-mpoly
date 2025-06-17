package it.unibo.monopoly.model.turnation.api;

/**
 * Iteraple interface for player collections.
 * give the iterator to the collection.
 */
public interface PlayerIterable {
    /**
     * create the iterator for the player collection.
     * @return Iterator
     */
    PlayerIterator iterator();
}
