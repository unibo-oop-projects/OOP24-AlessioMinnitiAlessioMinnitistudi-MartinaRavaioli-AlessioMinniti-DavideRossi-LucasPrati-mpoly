package it.unibo.monopoly.model.gameboard.api;

/**
 * iterator of pawn.
 * it gives you the pawn with getNext.
 * and tells you if has other pawns in it.
 */
public interface PawnIterator {
    /**
     * get you the next pawn in the iterator. 
     * @return Pawn
     */
    Pawn getNext();
    /**
     * tells if the iterator contains other pawns.
     * @return bool
     */
    boolean hasMore();
}
