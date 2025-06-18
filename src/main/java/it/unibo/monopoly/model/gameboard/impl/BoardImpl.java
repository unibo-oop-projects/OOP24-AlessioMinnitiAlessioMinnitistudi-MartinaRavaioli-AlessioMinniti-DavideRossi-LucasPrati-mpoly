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
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.ChancheAndCommunityChestDeck;
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
    private ChancheAndCommunityChestDeck deck;
    /**
     * constructor.
     * @param tiles list of tiles
     * @param pawns list of pawns
     * @param deck deck with chance and community cards
    */
    public BoardImpl(final List<Tile> tiles, final List<Pawn> pawns, final ChancheAndCommunityChestDeck deck) {
        this.tiles = new ArrayList<>(tiles);
        this.pawns = new ArrayList<>(pawns);
        this.deck = deck;
        sortTiles();
    }

    /**
     * constructor.
     * @param tiles list of tiles
     * @param pawns list of pawns
    */
    public BoardImpl(final List<Tile> tiles, final List<Pawn> pawns) {
        this.tiles = new ArrayList<>(tiles);
        this.pawns = new ArrayList<>(pawns);
        this.deck = new ChancheAndCommunityChestDeckImpl(List.of());
        sortTiles();
    }
    /**
     * constructor.
     */
    public BoardImpl() {
        this.tiles = new ArrayList<>();
        this.pawns = new ArrayList<>();
        this.deck = new ChancheAndCommunityChestDeckImpl(List.of());
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
                Tile tile = tiles.get(p.getPosition().getPos());
                if (tile instanceof Property) {
                    final Property prop = new NormalPropertyImpl(tile.getName(), tile.getPosition(), tile.getGroup());
                    if (tile instanceof BuildablePropertyImpl) {
                        return new BuildablePropertyImpl(prop);
                    }
                    return prop;
                } else {
                    return new SpecialImpl(tile.getName(), tile.getPosition(), Group.SPECIAL, 
                                                                ((Special) tile).getEffect());
                }
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
    public final int movePawn(final int id, final Collection<Integer> value) {
        Pawn pawn = null;
        for (final Pawn p : this.pawns) {
            if (((PawnImpl) p).getID().equals(id)) {
                pawn = p;
            }
        }

        Objects.requireNonNull(pawn);

        final int steps = value.stream().mapToInt(Integer::intValue).sum();
        pawn.move(steps);
        return pawn.getPosition().getPos() - pawn.getPreviousPosition().getPos();
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
                    final Property prop = new NormalPropertyImpl(t.getName(), t.getPosition(), t.getGroup());
                    if (t instanceof BuildablePropertyImpl) {
                        return new BuildablePropertyImpl(prop);
                    }
                    return prop;
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
    public final boolean canBuildHouseInProperty(final Property prop) {
        if (!prop.isBuildable()) {
            throw new IllegalArgumentException("this property can't build houses");
        }
        return prop.canBuildHouse();
    }

    @Override
    public final boolean canBuildHotelInProperty(final Property prop) {
        if (!prop.isBuildable()) {
            throw new IllegalArgumentException("this property can't build hotel");
        }
        return prop.canBuildHotel();
    }

    @Override
    public final boolean buildHouseInProperty(final Property prop) {
        if (!canBuildHouseInProperty(prop)) {
            throw new IllegalArgumentException("this property can't build the house");
        }
        prop.buildHouse();
        return true;
    }

    @Override
    public final boolean buildHotelInProperty(final Property prop) {
        if (!canBuildHotelInProperty(prop)) {
            throw new IllegalArgumentException("this property can't build the hotel");
        }
        prop.buildHotel();
        return true;
    }

    @Override
    public final boolean deleteHouseInProperty(final Property prop) throws IllegalAccessException {
        prop.deleteHouse();
        return true;
    }

    @Override
    public final boolean deleteHotelInProperty(final Property prop) throws IllegalAccessException {
        prop.deleteHotel();
        return true;
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
    public final ChanceAndCommunityChestCard draw() {
        return this.deck.draw();
    }

    @Override
    public final void addDeck(final ChancheAndCommunityChestDeck deck) {
        this.deck = deck;
    }

}
