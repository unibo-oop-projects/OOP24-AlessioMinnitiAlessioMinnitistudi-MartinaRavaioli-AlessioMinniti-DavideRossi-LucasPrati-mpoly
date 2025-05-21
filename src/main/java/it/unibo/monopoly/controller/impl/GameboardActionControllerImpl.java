package it.unibo.monopoly.controller.impl;

import java.util.List;

import it.unibo.monopoly.controller.api.GameboardActionController;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.turnation.api.Dice;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;
import it.unibo.monopoly.view.api.GameboardView;

public class GameboardActionControllerImpl implements GameboardActionController{
    private Board board;
    private TurnationManager turnManager;
    private GameboardView gameboardView;
        
    @Override
    public void startGame(int size, List<Player> players, List<Tile> tiles, Dice dice, List<Pawn> pawns) {
        turnManager = new TurnationManagerImpl(players,dice);
        board = new BoardImpl(tiles, pawns);
        gameboardView = new GameboardView(10); 
        gameboardView.show(players,tiles);
    }

    @Override
    public void gameOver() {
        turnManager.setOver();
    }

    @Override
    public void playerGameOver() {
        
    }

    @Override
    public void changePositions() {
        List<Integer> res = (List<Integer>) turnManager.moveByDices();
        ((BoardImpl) this.board).movePawn(((BoardImpl) this.board).getPawn(((TurnationManagerImpl) this.turnManager).getIdCurrPlayer()),res);
        gameboardView.changePos(((TurnationManagerImpl) this.turnManager).getIdCurrPlayer(),((BoardImpl) this.board).getPawn(((TurnationManagerImpl) this.turnManager).getIdCurrPlayer()).getPosition());
    }

    @Override
    public void addHouse(Property prop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addHouse'");
    }

    @Override
    public void addHotel(Property prop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addHotel'");
    }

    
}
