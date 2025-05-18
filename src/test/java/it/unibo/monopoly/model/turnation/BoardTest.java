package it.unibo.monopoly.model.turnation;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.gameboard.impl.PropertyImpl;
import it.unibo.monopoly.model.gameboard.impl.TileImpl;

public class BoardTest {
    private Board board;
    private Pawn pawn1, pawn2;
    private Tile tile1, tile2, tile3;
    private List<Tile> tiles;
    @BeforeEach
    void setUp() {
        tile1 = new PropertyImpl("a", 0, Group.RED);
        tile2 = new PropertyImpl("b", 1, Group.BLUE);
        tile3 = new PropertyImpl("c", 2, Group.YELLOW);
        tiles = List.of(tile1, tile2, tile3);
    }
}
