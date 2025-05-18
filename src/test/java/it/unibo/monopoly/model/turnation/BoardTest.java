package it.unibo.monopoly.model.turnation;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.PawnImpl;
import it.unibo.monopoly.model.gameboard.impl.PropertyImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

class BoardTest {
    private Board board;
    private Pawn pawn1, pawn2;
    private Tile tile1, tile2, tile3;
    private List<Tile> tiles;
    private List<Pawn> pawns;

    @BeforeEach
    void setUp() {
        tile1 = new PropertyImpl("a", new PositionImpl(0), Group.RED);
        tile2 = new PropertyImpl("b", new PositionImpl(1), Group.BLUE);
        tile3 = new PropertyImpl("c", new PositionImpl(2), Group.YELLOW);
        tiles = List.of(tile1, tile2, tile3);

        pawn1 = new PawnImpl(1, new PositionImpl(0), Color.RED);
        pawn2 = new PawnImpl(2, new PositionImpl(0), Color.BLUE);
        pawns = List.of(pawn1, pawn2);

        board = new BoardImpl(tiles, pawns);
    }

}
