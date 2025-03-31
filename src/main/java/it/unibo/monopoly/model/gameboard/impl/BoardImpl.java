package it.unibo.monopoly.model.gameboard.impl;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * board implementation.
*/
public class BoardImpl implements Board {
    // private final List<Tile> tiles;
    /**
     * constructor.
    */
    // public BoardImpl() {
    //     this.tiles = new ArrayList<>();
    // }
    // /**
    //  * constructor.
    //  * @param tiles
    // */
    // public BoardImpl(final List<Tile> tiles) {
    //     this.tiles = new ArrayList<>(tiles);
    // }
    //sell an house
    @Override
    public void sellHouse(final Property prop) { }
    //sell the entire proprierty
    @Override
    public void sellProperty(final Property prop) { }
    //get a tile
    @Override
    public final Tile getTile(final Position pos) { 
        return null; 
    }
    //buy a proprierty
    @Override
    public void buyProperty(final Property prop, final Player owner) { }
}
