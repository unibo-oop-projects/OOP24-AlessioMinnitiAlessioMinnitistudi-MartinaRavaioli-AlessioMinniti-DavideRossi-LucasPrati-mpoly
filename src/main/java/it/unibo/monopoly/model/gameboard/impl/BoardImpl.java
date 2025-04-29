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
    */
    public BoardImpl(final List<Tile> tiles, final List<Pawn> pawns) {
        this.tiles = tiles;
        this.pawns = pawns;
    }

    public void sortTiles(){
        this.tiles.sort((a, b) -> ((TileImpl)a).compareTo((TileImpl)b));
    }


    @Override
    public final Tile getTile(final Position pos) { 
        return tiles.get(pos.getPos());
    }

    public void removePawn(Pawn p){
        this.pawns.remove(p);
    }

    public void addPawn(Pawn p){
        this.pawns.add(p);
    }

    //buy a proprierty
    @Override
    public void buyProperty(final Property prop, final Player owner) {
    }

    @Override
    public Tile getTileForPawn(Pawn p) {
        return tiles.get(p.getPosition().getPos());
    }

    public List<Pawn> getPawninTile(Tile tile){
        List<Pawn> pawnsInTile=new ArrayList<>();

        for(Pawn p : pawnsInTile){
            if(((PositionImpl)p.getPosition()).equals((PositionImpl)tile.getPosition())){
                pawnsInTile.add(p);
            }
        }

        return pawnsInTile;
    }

    @Override
    public void movePawn(Pawn player, Collection<Integer> value) {
        int steps = value.stream().mapToInt(Integer::intValue).sum();
        player.move(steps);
    }
}
