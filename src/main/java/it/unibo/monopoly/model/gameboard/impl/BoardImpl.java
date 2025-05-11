package it.unibo.monopoly.model.gameboard.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

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
     * @param pawns
    */
    public BoardImpl(final List<Tile> tiles, final List<Pawn> pawns) {
        this.tiles = new ArrayList<>(tiles);
        this.pawns = new ArrayList<>(pawns);
    }
    /**
     * sort the tiles.
    */
    public void sortTiles() {
        this.tiles.sort((a, b) -> ((TileImpl) a).compareTo((TileImpl) b));
    }

    @Override
    public final Tile getTile(final Position pos) { 
        return tiles.get(pos.getPos());
    }
    /**
     * remove a pawn.
     * @param p
    */
    public void removePawn(final Pawn p) {
        this.pawns.remove(p);
    }
    /**
     * add a pawn.
     * @param p
    */
    public void addPawn(final Pawn p) {
        this.pawns.add(p);
    }

    //buy a proprierty
    @Override
    public void buyProperty(final Property prop, final Player owner) {
    }

    /**
     * get the tile of the pawn.
     * @param p
     * @return Tile
    */
    @Override
    public final Tile getTileForPawn(final Pawn p) {
        return tiles.get(p.getPosition().getPos());
    }
    /**
     * get the pawn on a tile.
     * @param tile
     * @return List of Pawn
    */
    public List<Pawn> getPawninTile(final Tile tile) {
        final List<Pawn> pawnsInTile = new ArrayList<>();

        for (final Pawn p : pawnsInTile) {
            if (((PositionImpl) p.getPosition()).equals((PositionImpl) tile.getPosition())) {
                pawnsInTile.add(p);
            }
        }

        return pawnsInTile;
    }

    @Override
    public final void movePawn(final Pawn player, final Collection<Integer> value) {
        final int steps = value.stream().mapToInt(Integer::intValue).sum();
        player.move(steps);
    }

    // public Pawn getPawn(int id) {
    //     return 
    // }
}
