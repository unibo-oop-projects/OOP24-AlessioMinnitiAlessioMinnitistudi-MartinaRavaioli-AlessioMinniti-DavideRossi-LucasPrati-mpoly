package it.unibo.monopoly.controller.impl;

import java.awt.Color;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;
import it.unibo.monopoly.view.api.GameboardView;
import it.unibo.monopoly.view.api.MainGameView;
import it.unibo.monopoly.model.transactions.api.BankAction;


/**
 * Implementation of {@link GameController}.
 */
public final class GameControllerImpl implements GameController {

    private static final int NUM = 0;

    private final Bank bank;
    private final TurnationManager turnationManager;
    private final Board board;
    private final Configuration config;
    private Map<String,BankAction> turnActions = new HashMap<>();
    private MainGameView gameView;
    private GameboardView gameboardView;

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
     * @param config a consistent configuration for settings
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
        if (bank.getTitleDeedsByOwner(player.getID()).isEmpty()) {
            return List.of();
        }
        return bank.getTitleDeedsByOwner(player.getID()).stream().toList();
    }

    @Override
    public BankAccount getPlayerBalance(final Player player) {
        return bank.getBankAccount(player.getID());
    }

    @Override
    public Color getPropertyColor(final TitleDeed selectedProperty) {
        return selectedProperty.getGroup().getColor();
    }


    @Override
    public void endTurn() {
        this.turnationManager.getNextPlayer();
    }

    @Override
    public void throwDices() {
        final Collection<Integer> result = this.turnationManager.moveByDices();
        this.board.movePawn(this.board.getPawn(this.turnationManager.getIdCurrPlayer()), result);
        this.gameboardView.changePos(this.turnationManager.getIdCurrPlayer(), 
                                    this.board.getPawn(this.turnationManager.getIdCurrPlayer()).getPosition());
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
        final UseFileTxt importRules = new UseFileTxtImpl();
        final String rules = importRules.loadTextResource(config.getRulesPath());
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

    @Override
    public void playerGameOver() {

    }

    @Override
    public void changePositions() {
        final Collection<Integer> res = turnationManager.moveByDices();
        this.board.movePawn(this.board.getPawn(this.turnationManager.getIdCurrPlayer()), res);
        gameboardView.changePos(this.turnationManager.getIdCurrPlayer(),
                                this.board.getPawn(this.turnationManager.getIdCurrPlayer()).getPosition());
    }

    @Override
    public void addHouse(final Property prop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addHouse'");
    }

    @Override
    public void addHotel(final Property prop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addHotel'");
    }

    @Override
    public int getSize(final int numTiles) {
        return numTiles / 4 + 1;
    }

    @Override
    public void gameOver() {
        this.turnationManager.setOver();
    }

    @Override
    public List<Tile> getTiles() {
        return Collections.unmodifiableList(this.board.getTiles());
    }

    @Override
    public List<Pawn> getPawns() {
        return Collections.unmodifiableList(this.board.getPawns());
    }

    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
                justification = "must return reference to the object instead of a copy")
    @Override
    public void setBoardView(final GameboardView view) {
        this.gameboardView = view;
    }

    @Override
    public Player getCurrPlayer() {
        return this.turnationManager.getCurrPlayer();
    }

    @Override
    public String getRentString(final TitleDeed selectedProperty, final Set<TitleDeed> collect) {
        final int rent = selectedProperty.getRent(collect, 1);
        if (selectedProperty.getGroup().equals(Group.SOCIETY)) {

            return rent + " times dice result";
        }
        return Integer.toString(rent);
    }

    @Override
    public void executeAction(final String actionName) {
        try {
            if (!turnActions.containsKey(actionName)) {
                throw new IllegalArgumentException("No action with this name was registered");
            }
            turnActions.get(actionName).executeTransaction();
            if (actionName == "buy") {
                final Property currentProperty = (Property) this.board.getTileForPawn(
                                                                        this.board.getPawn(
                                                                            this.turnationManager.getIdCurrPlayer()));
                gameboardView.buyProperty(currentProperty, this.turnationManager.getIdCurrPlayer());
            }
        } catch (final IllegalStateException | IllegalArgumentException e) {
            gameView.displayError(e);
        }
    }
}
