package it.unibo.monopoly.model.turnation;

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
    private Board board;
    private Pawn pawn1, pawn2;
    private List<Tile> tiles;
    private List<Pawn> pawns;

    @BeforeEach
    void setUp() {
        Tile tile1 = new PropertyImpl("a", new PositionImpl(0), Group.RED);
        Tile tile2 = new PropertyImpl("b", new PositionImpl(1), Group.BLUE);
        Tile tile3 = new PropertyImpl("c", new PositionImpl(2), Group.YELLOW);
        tiles = List.of(tile1, tile2, tile3);

        pawn1 = new PawnImpl(1, new PositionImpl(0), Color.RED);
        pawn2 = new PawnImpl(2, new PositionImpl(0), Color.BLUE);
        pawns = List.of(pawn1, pawn2);

        board = new BoardImpl(tiles, pawns);
    }

     @Test
    void testAddAndRemovePawn() {
        final Pawn newPawn = new PawnImpl(3, new PositionImpl(2), Color.GREEN);
        board.addPawn(newPawn);
        assertEquals(3, ((PawnImpl) board.getPawn(3)).getID());

        board.removePawn(newPawn);
        assertThrows(IllegalArgumentException.class, () -> board.getPawn(3));
    }

    @Test
    void testGetTileByPosition() {
        final Tile tile = board.getTile(new PositionImpl(1));
        assertEquals(1, tile.getPosition().getPos());
    }

    @Test
    void testGetTileForPawn() {
        final Tile tile = board.getTileForPawn(pawn2);
        assertEquals(0, tile.getPosition().getPos());
    }

    @Test
    void testMovePawn() {
        board.movePawn(pawn1, List.of(2, 3)); // Move 5 steps
        assertEquals(5, pawn1.getPosition().getPos());
    }

    @Test
    void testGetPawnById() {
        final Pawn foundPawn = board.getPawn(2);
        assertEquals(Color.BLUE, foundPawn.getColor());
    }

    @Test
    void testGetPawnThrowsIfIdNotFound() {
        assertThrows(IllegalArgumentException.class, () -> board.getPawn(99));
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
        assertEquals(4, unsortedBoard.getTile(new PositionImpl(0)).getPosition().getPos());
        assertEquals(7, unsortedBoard.getTile(new PositionImpl(1)).getPosition().getPos());
        assertEquals(12, unsortedBoard.getTile(new PositionImpl(2)).getPosition().getPos());
    }
}
