package it.unibo.monopoly.controller.impl;

import java.util.List;

import it.unibo.monopoly.controller.api.GameboardActionController;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.view.impl.GameboardViewImpl;

public class GameboardActionControllerImpl implements GameboardActionController{
    private Board board;
    private TurnationManager turnManager;
    private GameboardViewImpl gameboardView;
        
    public GameboardActionControllerImpl(final Board board, final TurnationManager turnManager) {
        this.board = board; 
        this.turnManager = turnManager;
    }

    @Override
    public void startGame(int size) {
        gameboardView = new GameboardViewImpl(11); 
        gameboardView.show(this.turnManager.getPlayerList(),this.board.getTiles());
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
        this.board.movePawn(this.board.getPawn(this.turnManager.getIdCurrPlayer()),res);
        gameboardView.changePos(this.turnManager.getIdCurrPlayer(),this.board.getPawn(this.turnManager.getIdCurrPlayer()).getPosition());
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

    @Override
    public int getSize(int numTiles) {
        return numTiles/4 + 1;
    }

    
}
