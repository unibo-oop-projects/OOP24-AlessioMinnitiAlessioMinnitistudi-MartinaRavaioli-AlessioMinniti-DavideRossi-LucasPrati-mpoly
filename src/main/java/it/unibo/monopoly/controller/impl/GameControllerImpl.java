package it.unibo.monopoly.controller.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Maps;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.PropertyAction;
import it.unibo.monopoly.model.transactions.api.PropertyActionsEnum;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;
import it.unibo.monopoly.view.api.MainGameView;
import it.unibo.monopoly.view.impl.MainViewImpl;


/**
 * Implementation of {@link GameController}.
 */
public final class GameControllerImpl implements GameController {
    private final TurnationManager turnationManager; /**turnation manager. */
    private final Board board; /**board. */
    private final Bank bank; /**bank. */
    private final Configuration config; /**config. */
    private final Map<PropertyActionsEnum, PropertyAction> turnActions = new EnumMap<>(PropertyActionsEnum.class);
    private MainGameView gameView; /**game view. */

    /**
     * Create a new {@link GameController} with the given parameters.
     * <p>
     * This constructor uses dependency injection to receive shared components of the game architecture.
     * {@link Board} and {@link TurnationManager} are mutable and intentionally injected without defensive copies,
     * as they are expected to maintain consistent shared state across the application.
     * 
     * @param board the game board
     * @param turnationManager the entity for manage the turnation of the players
     * @param config a consistent configuration for settings
     * @param bank the game's bank
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "Injection of shared mutable dependencies is intentional and controlled in this architecture."
    )
    public GameControllerImpl(
            final Board board,
            final TurnationManager turnationManager,
            final Configuration config,
            final Bank bank
        ) {
        this.board = board;
        this.turnationManager = turnationManager;
        this.config = config;
        this.bank = bank;
    }

    private void refreshPlayerInfo() {
        final Player currentPlayer = turnationManager.getCurrPlayer();
        gameView.displayPlayerInfo(currentPlayer, bank.getBankAccount(currentPlayer.getID()));
    }

    private void refreshCurrentTileInfo() {
        final int currentPlayerId = this.turnationManager.getIdCurrPlayer();
        final Tile currentlySittingTile = this.board.getTileForPawn(currentPlayerId);
        if (currentlySittingTile instanceof Property) {
            final String propertyName = currentlySittingTile.getName();
            this.gameView.displayPropertyContractInfo(this.bank.getTitleDeed(propertyName));
        } else {
            final Special specialTile = (Special) currentlySittingTile;
            this.gameView.displaySpecialInfo(specialTile);
        }
    }


    private void executeEffect(final Effect effect) {
        try {
            effect.activate(this.turnationManager.getCurrPlayer());
            this.gameView.displayMessage("Eseguito effetto: " + effect.getDescription());
            refreshPlayerInfo();
        } catch (final IllegalStateException | IllegalArgumentException e) {
            this.gameView.displayError(e);
        }
    }


    private void attachView(final MainGameView view) {
        this.gameView = view;
    }

    @Override
    public void endTurn() {
        try {
            if (!this.turnationManager.playerDiesIfTurnPassed()) {
                this.turnationManager.getNextPlayer();

                if (this.turnationManager.isCurrentPlayerParked()) {
                    this.gameView.displayMessage("you can't throw the dices and move, you are parked");
                }

                gameView.refreshUIForNewTurn(turnationManager.canThrowDices());
                refreshPlayerInfo();
            } else {
                this.gameView.displayOptionMessage("The player will die if he passes the turn");
            }
        } catch (final IllegalArgumentException e) {
            this.gameView.displayError(e);
        }
    }

    @Override
    public void throwDices() {
        try {
            final Pair<Collection<Integer>, String> result = this.turnationManager.moveByDices();
            final int currentPlayerId = this.turnationManager.getIdCurrPlayer();
            int delta = 0;

            if (!this.turnationManager.isCurrentPlayerInPrison()) {
                delta = this.board.movePawn(currentPlayerId, result.getLeft());
                if (result.getRight() != null) {
                    this.gameView.displayMessage(result.getRight());
                }
            } else {
                this.gameView.displayMessage(result.getRight());
            }

            this.gameView.callChangePositions();
            this.gameView.displayDiceResult(result.getLeft().stream().toList());
            final Tile currentlySittingTile = this.board.getTileForPawn(currentPlayerId);
            refreshCurrentTileInfo();
            if (currentlySittingTile instanceof Property) {
                this.turnActions.clear();
                this.turnActions.putAll(Maps.uniqueIndex(this.bank.getApplicableActionsForTitleDeed(currentPlayerId, 
                                        currentlySittingTile.getName(),
                                        result.getLeft().stream().mapToInt(d -> d).sum())
                                        .stream()
                                        .collect(Collectors.toMap(PropertyAction::getName, d -> d));
                this.gameView.displayPlayerActions(turnActions.keySet());
            } else if (currentlySittingTile instanceof Special) {
                final Special specialTile = (Special) currentlySittingTile;
                if (!"Start".equals(currentlySittingTile.getName()) && delta != 0) {
                    executeEffect(specialTile.getEffect());
                    this.gameView.callChangePositions();
                }
                refreshCurrentTileInfo();
            }

            if (delta < 0 && !"GoToJail".equals(currentlySittingTile.getName()) 
                && !"Jail / Just Visiting".equals(currentlySittingTile.getName())) {
                final Special tile = (Special) board.getTile("Start");
                executeEffect(tile.getEffect());
            }
            this.refreshBankPlayerInfo();
        } catch (final IllegalAccessException e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void loadRules() {
        final UseFileTxt importRules = new UseFileTxtImpl();
        final String rules = importRules.loadTextResource(config.getRulesPath());
        gameView.displayRules(rules);
    }

    @Override
    public Configuration getConfiguration() {
        return config;
    }

    @Override
    public void loadCurrentPlayerInformation() {
        gameView.displayPlayerStats(this.turnationManager.getCurrPlayer(), this.bank);
    }

    @Override
    public List<Tile> getTiles() {
        return Collections.unmodifiableList(this.board.getTiles());
    }

    @Override
    public List<Pawn> getPawns() {
        return Collections.unmodifiableList(this.board.getPawns());
    }

    @Override
    public Player getCurrPlayer() {
        return this.turnationManager.getCurrPlayer();
    }

    @Override
    public Pawn getCurrPawn() {
        return this.board.getPawn(this.turnationManager.getIdCurrPlayer());
    }

    @Override
    public void executeAction(final PropertyActionsEnum actionName) {

        if (!turnActions.containsKey(actionName)) {
                gameView.displayError(new IllegalArgumentException("No action with this name was registered. " 
                + "It is possible that the current "
                + "player has no permission to execute this action on the selected title deed"));
                return;
        }

        try {
            final PropertyAction action = turnActions.get(actionName);
            action.executePropertyAction(board, bank);
            gameView.displayMessage(action.getDescription() + " eseguita con successo");
            final Property currentlySittingProperty = (Property) this.board.getTileForPawn(
                                                        this.turnationManager.getIdCurrPlayer());
            switch (actionName) {
                case BUY -> gameView.callBuyProperty(currentlySittingProperty.getName(), currentlySittingProperty.getGroup().getColor());
                case SELL -> gameView.callClearPanel(currentlySittingProperty.getName());
                case BUYHOUSE -> gameView.callBuyHouse(currentlySittingProperty);
                case BUYHOTEL -> gameView.callBuyHotel(currentlySittingProperty);
                case SELLHOUSE -> gameView.callSellHouse(currentlySittingProperty);
                case SELLHOTEL -> gameView.callSellHotel(currentlySittingProperty);
                default -> {
                }
            }
            refreshPlayerInfo();
            refreshCurrentTileInfo();
        } catch (final IllegalStateException | IllegalArgumentException e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void start() {
        attachView(new MainViewImpl(this));
        this.turnationManager.resetBankState();
        gameView.refreshUIForNewTurn(turnationManager.canThrowDices());
        refreshPlayerInfo();
    }

    @Override
    public List<Pair<String, Integer>> getRanking() {
        return this.turnationManager.getRanking();
    }

    @Override
    public Pair<String, Integer> getWinner() {
        return this.turnationManager.getWinner();
    }

    @Override
    public void endTurnPlayerDies() {
        if (!this.turnationManager.playerDiesIfTurnPassed()) {
            if (this.turnationManager.canPassTurn()) {
                this.turnationManager.getNextPlayer();
            } else {
                this.gameView.displayMessage("The player has some actions to do before passing the turn");
            }
        } else {
            final String deadPlayer = this.turnationManager.getCurrPlayer().getName();
            this.gameView.callDeletePlayer(this.turnationManager.getCurrPlayer().getColor(), 
                                            this.turnationManager.getIdCurrPlayer());

            this.board.removePawn(this.turnationManager.getIdCurrPlayer());
            this.turnationManager.deletePlayer(this.turnationManager.getCurrPlayer());
            this.gameView.displayMessage("Player " + deadPlayer + " is dead");

            if (this.turnationManager.isOver()) {
                this.gameView.showRanking();
            }
        }
        gameView.refreshUIForNewTurn(turnationManager.canThrowDices());
        refreshPlayerInfo();
    }

    @Override
    public void refreshBankPlayerInfo() {
        if (null != this.gameView) {
            this.gameView.callClearAll();

            for (final Player p : this.turnationManager.getPlayerList()) {
                for (final TitleDeed t : this.bank.getTitleDeedsByOwner(p.getID())) {
                    this.gameView.callBuyProperty(t.getName(), p.getColor());
                }
            }
        }
    }
}
