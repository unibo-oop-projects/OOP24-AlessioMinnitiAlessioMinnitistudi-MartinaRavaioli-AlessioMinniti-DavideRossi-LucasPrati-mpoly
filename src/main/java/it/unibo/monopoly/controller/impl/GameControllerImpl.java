package it.unibo.monopoly.controller.impl;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.view.api.MainGameView;

/**
 * implementation of game controller.
 */
public final class GameControllerImpl implements GameController {

    private static final int NUM = 0;
    private final Bank bank; 
    private  Board board;
    private  MainGameView gameView;
    private TurnationManager manager;

    /**
     * constructor for this class.
     * @param bank
     */
    public GameControllerImpl(final Bank bank) {
        this.bank = bank; 
    }

    @Override
    public boolean areThereHouses(final TitleDeed prop) {
        return prop.houseNum() > 0;
    }

    @Override
    public boolean sellHouse(final List<TitleDeed> properties, final Object selectedValue) {
        //manac metodo rossi per far arrivare i soldi al giocatore che vende
        /*final int propInd = getPropertyIndex(properties, selectedValue);
        final int houses = properties.get(propInd).houseNum();
        properties.get(propInd).setHouseNum(houses - 1);*/
        return true;
    }

    @Override
    public boolean sellProperty(final TitleDeed selectedProperty) {
        bank.sellTitleDeed(selectedProperty.getName());
        return true;
    }

    @Override
    public TitleDeed getProperty(final List<TitleDeed> properties, final Object selectedValue) {
        final Optional<TitleDeed> selectedPropertyO = properties.stream()
                                                                .filter(p -> p.getName().equals(selectedValue))
                                                                .findAny();
        TitleDeed selectedProperty = new BaseTitleDeed(null, "null", NUM, null, NUM); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty = selectedPropertyO.get();
        }
        return selectedProperty;
    }

    @Override
    public List<TitleDeed> getProperties(final Player player) {
        if (bank.getTitleDeedsByOwner(player.getName()).isEmpty()) {
            return List.of();
        }
        return bank.getTitleDeedsByOwner(player.getName()).stream().toList();
    }

    @Override
    public int getPlayerBalance(final Player player) {
        return bank.getBankAccount(player.getName()).getBalance();
    }

    @Override
    public Color getPropertyColor(final TitleDeed selectedProperty) {
        final Group colorS = selectedProperty.getGroup();
        final Color color;
        switch (colorS) {
            case Group.BLUE:
                color = Color.BLUE;
                break;
            case Group.RED:
                color = Color.RED;
                break;
            case Group.GREEN:
                color = Color.GREEN;
                break;
            case Group.YELLOW:
                color = Color.YELLOW;
                break;
            case Group.ORANGE:
                color = Color.ORANGE;
                break;
            case Group.BLACK:
                color = Color.BLACK;
                break;
            default:
                color = Color.WHITE;
                break;
        }
        return color;
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
            final Tile currentPlayerTile = board.getTileForPawn(board.getPawn(manager.getIdCurrPlayer()));
            bank.buyTitleDeed(currentPlayerTile.toString(), null);
        } catch (final Exception e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void payPropertyOwner() {
        try {
            final Tile currentPlayerTile = board.getTileForPawn(board.getPawn(manager.getIdCurrPlayer()));
            bank.payRent(currentPlayerTile.toString(), null);
        } catch (final Exception e) {
            gameView.displayError(e);
        }
    }

    @Override
    public Player getCurrentPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentPlayer'");
    }

    @Override
    public BankAccount getPlayerAccount(final Player currentPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerAccount'");
    }

    @Override
    public void loadRules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadRules'");
    }
}
