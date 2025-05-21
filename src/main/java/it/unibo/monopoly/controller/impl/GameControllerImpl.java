package it.unibo.monopoly.controller.impl;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.utils.ResourceLoader;
import it.unibo.monopoly.view.api.MainGameView;


/**
 * Implementation of {@link GameController}.
 */
public final class GameControllerImpl implements GameController {

    private static final int NUM = 0;

    private final Bank bank;
    private final TurnationManager turnationManager;        // TODO
    private final Board board;                              // TODO
    private final Configuration config;
    private MainGameView gameView;

    /**
     * Create a new {@link GameController} with the given parameters.
     * <p>
     * This constructor uses dependency injection to receive shared components of the game architecture.
     * {@link Board} and {@link TurnationManager} are mutable and intentionally injected without defensive copies,
     * as they are expected to maintain consistent shared state across the application.
     * 
     * @param bank the bank of the game
     * @param board the game board
     * @param turnationManager the entity for manage the turnation of the players
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "Injection of shared mutable dependencies is intentional and controlled in this architecture."
    )
    public GameControllerImpl(
            final Bank bank,
            final Board board,
            final TurnationManager turnationManager,
            final Configuration config
        ) {
        this.bank = bank;
        this.board = board;
        this.turnationManager = turnationManager;
        this.config = config;
        this.board.getClass(); // TODO rimuovere, utilizzato per bypassare warning 'unused' per fare build
        this.turnationManager.getClass(); // TODO rimuovere, utilizzato per bypassare warning 'unused' per fare build
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
    public BankAccount getPlayerBalance(final Player player) {
        return bank.getBankAccount(player.getName());
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
            //MISSING IDENTIFIER INTEGRATION WITH  BANK
            //final Tile currentPlayerTile = board.getTileForPawn(board.getPawn(manager.getIdCurrPlayer()));
            //bank.buyTitleDeed(currentPlayerTile.toString(), null);
            gameView.displayMessage("Purchase of title deed successful");
            throw new UnsupportedOperationException("Unimplemented method 'buyProperty'");
        } catch (final IllegalStateException e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void payPropertyOwner() {
        try {
            //MISSING IDENTIFIER INTEGRATION WITH  BANK
            //final Tile currentPlayerTile = board.getTileForPawn(board.getPawn(manager.getIdCurrPlayer()));
            //bank.payRent(currentPlayerTile.toString(), null);
            gameView.displayMessage("Rent payment successful");
            throw new UnsupportedOperationException("Unimplemented method 'payPropertyOwner'");
        } catch (final IllegalStateException e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void loadRules() {
        final String rules = ResourceLoader.loadTextResource(config.getRulesPath());
        gameView.showRules(rules);
    }

    @Override
    public Configuration getConfiguration() {
        return config;
    }

    @Override
    public void loadCurrentPlayerInformation() {
        try {
            //Missing method to get current player
            //gameView.displayPlayerStats();
            throw new UnsupportedOperationException("Unimplemented method 'loadCurrentPlayerInformation'");
        } catch (final IllegalStateException e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void attachView(final MainGameView view) {
        this.gameView = view;
    }
}
