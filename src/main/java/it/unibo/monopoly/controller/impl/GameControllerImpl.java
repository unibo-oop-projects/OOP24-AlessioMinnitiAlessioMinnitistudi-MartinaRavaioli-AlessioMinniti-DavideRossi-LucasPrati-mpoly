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
    /**
     * this method returns wether there are houses on the property.
     * @param prop the property you want to know if there are houses
     * @return wether there are houses on the property
     */
    public boolean areThereHouses(final TitleDeed prop) {
        return prop.houseNum() > 0;
    }

    @Override
    /**
     * this method removes a house fromm the property. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedValue the property from wich the house will be removed
     * @return wether the payment has been succesful
     */
    public boolean sellHouse(final List<TitleDeed> properties, final Object selectedValue) {
        //manac metodo rossi per far arrivare i soldi al giocatore che vende
        /*final int propInd = getPropertyIndex(properties, selectedValue);
        final int houses = properties.get(propInd).houseNum();
        properties.get(propInd).setHouseNum(houses - 1);*/
        return true;
    }

    @Override
    /**
     * this method removes the property fromm the property list. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedProperty the property you want to sell
     * @return wether the payment has been succesful
     */
    public boolean sellProperty(final TitleDeed selectedProperty) {
        bank.sellTitleDeed(selectedProperty.getName());
        return true;
    }

    @Override
    /**
     * 
     * @param properties the players property list
     * @param selectedValue the property you want 
     * @return the property 
     */
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

    /**
     * private method to get the index of the selected value from the list.
     * @param properties
     * @param selectedValue
     * @return index of the selected value 
     */
    /*private int getPropertyIndex(final List<TitleDeed> properties, final Object selectedValue) {
        final Optional<TitleDeed> selectedPropertyO = properties.stream()
                                                                .filter(p -> p.getName().equals(selectedValue))
                                                                .findAny();
        TitleDeed selectedProperty = new BaseTitleDeed("null", "null", NUM, null, NUM); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty = selectedPropertyO.get();
        }
        return properties.indexOf(selectedProperty);
    }*/

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
            Tile currentPlayerTile = board.getTileForPawn(board.getPawn(manager.getIdCurrPlayer()));
            bank.buyTitleDeed(currentPlayerTile.toString(), null);
        } catch (Exception e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void payPropertyOwner() {
        try {
            Tile currentPlayerTile = board.getTileForPawn(board.getPawn(manager.getIdCurrPlayer()));
            bank.payRent(currentPlayerTile.toString(), null);
        } catch (Exception e) {
            gameView.displayError(e);
        }
    }

    @Override
    public Player getCurrentPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentPlayer'");
    }

    @Override
    public BankAccount getPlayerAccount(Player currentPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerAccount'");
    }
}
