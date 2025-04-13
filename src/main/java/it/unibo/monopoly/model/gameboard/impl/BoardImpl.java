package it.unibo.monopoly.model.gameboard.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * board implementation.
*/
public class BoardImpl implements Board {
    private final List<Tile> tiles;
    private final List<Pawn> pawns;
    /**
     * constructor.
    */
    public BoardImpl() {
        this.tiles = new ArrayList<>();
        this.pawns = new ArrayList<>();
    }
    /**
     * constructor.
     * @param tiles
    */
    public BoardImpl(final List<Tile> tiles, final List<Pawn> pawns) {
        this.tiles = tiles;
        this.pawns = pawns;
    }

    //sell an house
    @Override
    public void sellHouse(final Property prop) { }
    //sell the entire proprierty

    @Override
    public void sellProperty(final Property prop) { }
    //get a tile

    @Override
    public final Tile getTile(final Position pos) { 
        return tiles.get(pos.getPos());
    }

    //buy a proprierty
    @Override
    public void buyProperty(final Property prop, final Player owner) {
    }

    @Override
    public Tile getTileForPawn(Pawn p) {
        return tiles.get(p.getPosition().getPos());
    }

    @Override
    public void movePawn(Pawn player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movePawn'");
    }
}
