package it.unibo.monopoly.model.gameboard.impl;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

//implementation of the board
public class BoardImpl implements Board {
    @SuppressWarnings("unused")
    private List<Tile> tiles;
    //constructor
    public BoardImpl(){}
    //sell an house
    @Override
    public void sellHouse(Property prop) {}
    //sell the entire proprierty
    @Override
    public void sellProperty(Property prop) {}
    //get a tile
    @Override
    public Tile getTile(Position pos) { return null; }
    //buy a proprierty
    @Override
    public void buyProperty(Property prop, Player owner) {}
}
