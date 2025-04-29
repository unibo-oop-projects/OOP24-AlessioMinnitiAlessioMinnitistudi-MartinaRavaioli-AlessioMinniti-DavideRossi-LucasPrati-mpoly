package it.unibo.monopoly.controller.impl;

import java.util.List;

import it.unibo.monopoly.controller.api.GameboardActionController;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.GameboardView;

public class GameboardActionControllerImpl implements GameboardActionController{

    @Override
    public void startGame(int size, List<Player> players, List<Tile> tiles) {
        new GameboardView(10,players,null); 
    }

    @Override
    public void gameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gameOver'");
    }
    
}
