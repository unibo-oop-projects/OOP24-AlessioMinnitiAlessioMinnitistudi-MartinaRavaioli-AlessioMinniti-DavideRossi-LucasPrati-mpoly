package it.unibo.monopoly.model.gameboard;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.PawnImpl;
import it.unibo.monopoly.model.gameboard.impl.PropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.TileImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

class BoardTest {
    private static final int STEPS = 5;
    private static final int UNUSED_ID = 99;
    private Board board;
    private Pawn pawn1, pawn2;

    @BeforeEach
    void setUp() {
        final Tile tile1 = new PropertyImpl("a", new PositionImpl(0), Group.RED);
        final Tile tile2 = new PropertyImpl("b", new PositionImpl(1), Group.BLUE);
        final Tile tile3 = new PropertyImpl("c", new PositionImpl(2), Group.YELLOW);
        final List<Tile> tiles = List.of(tile1, tile2, tile3);

        pawn1 = new PawnImpl(1, new PositionImpl(0), Color.RED);
        pawn2 = new PawnImpl(2, new PositionImpl(0), Color.BLUE);
        final List<Pawn> pawns = List.of(pawn1, pawn2);

        board = new BoardImpl(tiles, pawns);
    }

     @Test
    void testAddAndRemovePawn() {
        final Pawn newPawn = new PawnImpl(3, new PositionImpl(2), Color.GREEN);
        board.addPawn(newPawn);
        assertEquals(3, ((PawnImpl) board.getPawn(3)).getID());

        board.removePawn(((PawnImpl) newPawn).getID());
        assertThrows(IllegalArgumentException.class, () -> board.getPawn(3));
    }

    @Test
    void testGetTileByPosition() {
        final Tile tile = board.getTile(new PositionImpl(1));
        assertEquals(1, tile.getPosition().getPos());
    }

    @Test
    void testGetTileForPawn() {
        final Tile tile = board.getTileForPawn(((PawnImpl) pawn2).getID());
        assertEquals(0, tile.getPosition().getPos());
    }

    @Test
    void testMovePawn() {
        board.movePawn(((PawnImpl) pawn1).getID(), List.of(2, 3)); // Move 5 steps
        assertEquals(STEPS, pawn1.getPosition().getPos());
    }

    @Test
    void testGetPawnById() {
        final Pawn foundPawn = board.getPawn(2);
        assertEquals(Color.BLUE, foundPawn.getColor());
    }

    @Test
    void testGetPawnThrowsIfIdNotFound() {
        assertThrows(IllegalArgumentException.class, () -> board.getPawn(UNUSED_ID));
    }

    @Test
    void testGetPawnInTile() {
        final List<Pawn> pawnsInTile1 = board.getPawninTile(board.getTile(new PositionImpl(0)));
        assertEquals(2, pawnsInTile1.size());
        assertEquals(((PawnImpl) pawn1).getID(), ((PawnImpl) pawnsInTile1.get(0)).getID());
        assertEquals(((PawnImpl) pawn2).getID(), ((PawnImpl) pawnsInTile1.get(1)).getID());
    }

    @Test
    void testGetPawnInEmptyTile() {
        final Tile emptyTile = new PropertyImpl("d", new PositionImpl(5), Group.BLACK);
        final List<Pawn> pawns = board.getPawninTile(emptyTile);
        assertTrue(pawns.isEmpty());
    }

    @Test
    void testSortTiles() {
        final TileImpl t1 = new PropertyImpl("t1", new PositionImpl(12), Group.BLACK);
        final TileImpl t2 = new PropertyImpl("t2", new PositionImpl(4), Group.BLUE);
        final TileImpl t3 = new PropertyImpl("t3", new PositionImpl(7), Group.CYAN);
        final BoardImpl unsortedBoard = new BoardImpl(Arrays.asList(t1, t2, t3), Collections.emptyList());

        unsortedBoard.sortTiles();
        assertEquals(t2.getPosition().getPos(), unsortedBoard.getTile(new PositionImpl(0)).getPosition().getPos());
        assertEquals(t3.getPosition().getPos(), unsortedBoard.getTile(new PositionImpl(1)).getPosition().getPos());
        assertEquals(t1.getPosition().getPos(), unsortedBoard.getTile(new PositionImpl(2)).getPosition().getPos());
    }
}
