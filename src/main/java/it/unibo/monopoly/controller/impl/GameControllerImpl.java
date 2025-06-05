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
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;
import it.unibo.monopoly.view.api.MainGameView;
import it.unibo.monopoly.model.transactions.api.PropertyActionsEnum;


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
        gameView.refreshCurrentPlayerInfo(currentPlayer, bank.getBankAccount(currentPlayer.getID()));
    }

    private void refreshCurrentTileInfo() {
        final int currentPlayerId = this.turnationManager.getIdCurrPlayer();
        final Tile currentlySittingTile = this.board.getTileForPawn(currentPlayerId);
        if (currentlySittingTile instanceof Property) {
            final String propertyName = currentlySittingTile.getName();
            this.gameView.displayPropertyContract(this.bank.getTitleDeed(propertyName));
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


    @Override
    public void endTurn() {
        if (!this.turnationManager.playerDiesIfTurnPassed()) {
            if (this.turnationManager.canPassTurn()) {
                this.turnationManager.getNextPlayer();
                gameView.clearControlsUI();
                refreshPlayerInfo();
            } else {
                this.gameView.displayMessage("The player has some actions to do before passing the turn");
            }
        } else {
            this.gameView.displayOptionMessageEndTurn("The player will die if he passes the turn");
            //this.gameView.displayMessage("The player will die if he passes the turn");
        }

    }

    @Override
    public void throwDices() {
        try {
            final Collection<Integer> result = this.turnationManager.moveByDices();
            if (/*!this.turnationManager.isCurrentPlayerParked()
                &&*/ !this.turnationManager.isCurrentPlayerInPrison() 
                || this.turnationManager.canExitPrison(result)
                 ) {


            final int currentPlayerId = this.turnationManager.getIdCurrPlayer();
            this.board.movePawn(currentPlayerId, result);
            this.gameView.callChangePositions();
            this.gameView.displayDiceResult(result.stream().toList());
            final Tile currentlySittingTile = this.board.getTileForPawn(currentPlayerId);
            refreshCurrentTileInfo();
            if (currentlySittingTile instanceof Property) {
                this.turnActions.clear();
                this.turnActions.putAll(Maps.uniqueIndex(this.bank.getApplicableActionsForTitleDeed(currentPlayerId, 
                                        currentlySittingTile.getName(), 
                                        result.stream().mapToInt(d -> d).sum()),
                                        PropertyAction::getType));
                this.gameView.showPlayerActions(turnActions.keySet());
            } else if (currentlySittingTile instanceof Special) {
                final Special specialTile = (Special) currentlySittingTile;
                if (!"Go".equals(currentlySittingTile.getName())) {
                    executeEffect(specialTile.getEffect());
                    this.gameView.callChangePositions();
                }
            }
        }
        } catch (final IllegalAccessException e) {
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
        gameView.displayPlayerStats(this.turnationManager.getCurrPlayer(), this.bank);
    }

    @Override
    public void attachView(final MainGameView view) {
        this.gameView = view;
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
            if (actionName == PropertyActionsEnum.BUY) {
                gameView.callBuyProperty(currentlySittingProperty);
            } else if (actionName == PropertyActionsEnum.SELL) {
                gameView.callClearPanel(currentlySittingProperty.getName());
            }
            refreshPlayerInfo();
            refreshCurrentTileInfo();
        } catch (final IllegalStateException | IllegalArgumentException e) {
            gameView.displayError(e);
        }
    }

    @Override
    public void start() {
        this.turnationManager.resetBankState();
        gameView.clearControlsUI();
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
            this.turnationManager.deletePlayer(this.turnationManager.getCurrPlayer());
            this.turnationManager.getNextPlayer();
            this.gameView.displayMessage("Player " + deadPlayer + " is dead");
        }
        gameView.clearControlsUI();
        refreshPlayerInfo();
    }
}
