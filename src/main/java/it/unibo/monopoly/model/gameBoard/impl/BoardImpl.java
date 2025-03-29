package it.unibo.monopoly.model.gameboard.impl;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;

public class BoardImpl implements Board {
    @SuppressWarnings("unused")
    List<Tile> tiles;

    public BoardImpl(){
        
    }
    @Override
    public void sellHouse(Property prop) {
    }
    @Override
    public void sellProperty(Property prop) {
    }
    @Override
    public Tile getTile(Position pos) { return null; }
    @Override
    public void buyProperty(Property prop, Player owner) {}
}
