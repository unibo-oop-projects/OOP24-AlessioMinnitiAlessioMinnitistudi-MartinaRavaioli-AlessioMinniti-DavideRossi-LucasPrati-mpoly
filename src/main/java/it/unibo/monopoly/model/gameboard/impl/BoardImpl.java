package it.unibo.monopoly.model.gameboard.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.Tile;
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
    @Override
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
    @Override
    public void removePawn(final Pawn p) {
        this.pawns.remove(p);
    }

    /**
     * add a pawn.
     * @param p
    */
    @Override
    public void addPawn(final Pawn p) {
        this.pawns.add(p);
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
    @Override
    public List<Pawn> getPawninTile(final Tile tile) {
        final List<Pawn> pawnsInTile = new ArrayList<>();

        for (final Pawn p : this.pawns) {
            if (((PositionImpl) p.getPosition()).equals((PositionImpl) tile.getPosition())) {
                pawnsInTile.add(p);
            }
        }

        return pawnsInTile;
    }

    /**
     * move the pawn.
     * @param player
     * @param value
    */
    @Override
    public final void movePawn(final Pawn player, final Collection<Integer> value) {
        final int steps = value.stream().mapToInt(Integer::intValue).sum();
        player.move(steps);
    }

    /**
     * get the pawn of the id given.
     * @param id
     * @return Pawn
    */
    @Override
    public Pawn getPawn(final int id) {
        for (final Pawn p : this.pawns) {
            if (((PawnImpl) p).getID() == id) {
                return new PawnImpl(id, p.getPosition(), p.getColor());
            }
        }

        throw new IllegalArgumentException("id not present");
    }

    /**
     * get all the tiles.
     * @return List of Tiles
    */
    @Override
    public final List<Tile> getTiles() {
        return Collections.unmodifiableList(this.tiles);
    }

    @Override
    public final void movePawnInTile(final Pawn pawn, final String name) {
        final Tile tile = getTile(name);
        pawn.setPosition(tile.getPosition());
    }

    @Override
    public final Tile getTile(final String name) {
        for (final Tile t : this.tiles) {
            if (t.getName().equals(name)) {
                if (t instanceof Property) {
                    return new PropertyImpl(t.getName(), t.getPosition(), t.getGroup());
                } else {
                    return new SpecialImpl(t.getName(), t.getPosition(), ((Special) t).getEffect());
                }
            }
        }

        throw new IllegalArgumentException("name not found");
    }

}
