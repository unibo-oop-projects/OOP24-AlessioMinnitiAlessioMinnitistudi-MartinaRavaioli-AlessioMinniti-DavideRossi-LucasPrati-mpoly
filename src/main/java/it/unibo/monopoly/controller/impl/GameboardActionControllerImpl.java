package it.unibo.monopoly.controller.impl;

import java.util.List;

import it.unibo.monopoly.controller.api.GameboardActionController;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;
import it.unibo.monopoly.view.api.GameboardView;

public class GameboardActionControllerImpl implements GameboardActionController{
    private Board gameboard=new BoardImpl();
    private TurnationManager turnManager=new TurnationManagerImpl();
        
    @Override
    public void startGame(int size, List<Player> players, List<Tile> tiles) {
        new GameboardView(10,players,null); 
    }

    @Override
    public void gameOver() {
        turnManager.setOver();
    }

    @Override
    public void playerGameOver() {
        
    }
    
}
