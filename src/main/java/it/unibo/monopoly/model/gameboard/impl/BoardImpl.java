package it.unibo.monopoly.model.gameboard.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ChanceAndCommunityChestCard;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ChancheAndCommunityChestDeckImpl;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;

/**
 * board implementation.
*/
public class BoardImpl implements Board {
    private final List<Tile> tiles; /**list of tiles. */
    private final List<Pawn> pawns; /**list of pawns. */
    private final ChancheAndCommunityChestDeckImpl chanceAndCommunityChest;
    /**
     * constructor.
    */
    public BoardImpl() {
        this.tiles = new ArrayList<>();
        this.pawns = new ArrayList<>();
        this.chanceAndCommunityChest = new ChancheAndCommunityChestDeckImpl(null, null);
    }

    /**
     * constructor.
     * @param tiles list of tiles
     * @param pawns list of pawns
    */
    public BoardImpl(final List<Tile> tiles, final List<Pawn> pawns, final ChancheAndCommunityChestDeckImpl chanceAndCommunityChest) {
        this.tiles = new ArrayList<>(tiles);
        this.pawns = new ArrayList<>(pawns);
        this.chanceAndCommunityChest = chanceAndCommunityChest;
    }

    @Override
    public final void sortTiles() {
        this.tiles.sort((a, b) -> ((TileImpl) a).compareTo((TileImpl) b));
    }

    @Override
    public final Tile getTile(final Position pos) { 
        return tiles.get(pos.getPos());
    }

    @Override
    public final void removePawn(final int id) {
        this.pawns.removeIf(p -> ((PawnImpl) p).getID().equals(id));
    }

    @Override
    public final void addPawn(final Pawn p) {
        this.pawns.add(p);
    }

    @Override
    public final Tile getTileForPawn(final int id) {
        for (final Pawn p : this.pawns) {
            if (((PawnImpl) p).getID().equals(id)) {
                return tiles.get(p.getPosition().getPos());
            }
        }

        throw new IllegalArgumentException("id not present");
    }

    @Override
    public final List<Pawn> getPawninTile(final Tile tile) {
        final List<Pawn> pawnsInTile = new ArrayList<>();

        for (final Pawn p : this.pawns) {
            if (((PositionImpl) p.getPosition()).equals((PositionImpl) tile.getPosition())) {
                pawnsInTile.add(p);
            }
        }

        return pawnsInTile;
    }

    @Override
    public final void movePawn(final int id, final Collection<Integer> value) {
        Pawn pawn = null;
        for (final Pawn p : this.pawns) {
            if (((PawnImpl) p).getID().equals(id)) {
                pawn = p;
            }
        }

        Objects.requireNonNull(pawn);

        final int steps = value.stream().mapToInt(Integer::intValue).sum();
        pawn.move(steps);
    }

    @Override
    public final Pawn getPawn(final int id) { //it's used to return the pawn outside of the board, it's because it returns a copy 
        for (final Pawn p : this.pawns) {
            if (((PawnImpl) p).getID() == id) {
                return new PawnImpl(((PawnImpl) p).getID(), p.getPosition(), p.getColor());
            }
        }

        throw new IllegalArgumentException("id not present");
    }

    @Override
    public final List<Tile> getTiles() {
        return Collections.unmodifiableList(this.tiles);
    }

    @Override
    public final List<Pawn> getPawns() {
        return Collections.unmodifiableList(this.pawns);
    }

    @Override
    public final void movePawnInTile(final int id, final String name) {
        Pawn pawn = null;
        for (final Pawn p : this.pawns) {
            if (((PawnImpl) p).getID() == id) {
                pawn = p;
            }
        }
        Objects.requireNonNull(pawn);
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
                    return new SpecialImpl(t.getName(), t.getPosition(), Group.SPECIAL, 
                                                                ((Special) t).getEffect());
              }
            }
        }

        throw new IllegalArgumentException("name not found");
    }

    @Override
    public final void addTile(final Tile tile) {
        this.tiles.add(tile);
    }

    @Override
    public final Position getPrevPawnPosition(final int id) {
        for (final Pawn p : this.pawns) {
            if (((PawnImpl) p).getID().equals(id)) {
                return p.getPreviousPosition();
            }
        }
        throw new IllegalArgumentException("id not present");
    }

    @Override
    public ChanceAndCommunityChestCard drawCard() {
        return this.chanceAndCommunityChest.draw();
    }

}
