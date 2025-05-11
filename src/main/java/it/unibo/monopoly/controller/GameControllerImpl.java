package it.unibo.monopoly.controller;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.view.api.MainGameView;

public class GameControllerImpl implements GameController{

    private final Bank bank;
    private final MainGameView gameView;

    public GameControllerImpl(Bank bank, MainGameView view) {
        this.bank = bank;
        this.gameView = view;
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }

    @Override
    public void throwDices() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'throwDices'");
    }

    @Override
    public void buyProperty() {
        try {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'buyProperty'");   
        } catch (Exception e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void payPropertyOwner() {
        try {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'payPropertyOwner'");
        } catch (Exception e) {
            gameView.displayError(e);
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'payPropertyOwner'");
    }

    @Override
    public void sellProperty(final String titledeedName) {
        try {
            bank.sellTitleDeed(titledeedName);
            //eventuale view update?
        } catch (Exception e) {
            gameView.displayError(e);
        }
    }

}
