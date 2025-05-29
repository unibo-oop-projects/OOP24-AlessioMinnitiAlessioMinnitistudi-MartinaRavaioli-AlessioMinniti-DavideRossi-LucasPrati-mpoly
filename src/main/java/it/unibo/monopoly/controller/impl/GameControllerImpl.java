package it.unibo.monopoly.controller.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Effect;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.gameboard.api.Special;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;
import it.unibo.monopoly.view.api.MainGameView;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAction;


/**
 * Implementation of {@link GameController}.
 */
public final class GameControllerImpl implements GameController {
    private final TurnationManager turnationManager; /**turnation manager. */
    private final Board board; /**board. */
    private final Configuration config; /**config. */
    private final Bank bank;
    private Map<String,BankAction> turnActions = new HashMap<>();
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


    private void executeEffect(final Effect effect) {
        try {
            effect.activate(this.turnationManager.getCurrPlayer());
        } catch (final Exception e) {
            this.gameView.displayError(e);
        }
    }


    @Override
    public void endTurn() {
        this.turnationManager.getNextPlayer();
    }

    @Override
    public void throwDices() {
        final Collection<Integer> result = this.turnationManager.moveByDices();
        final int currentPlayerId = this.turnationManager.getIdCurrPlayer();
        this.board.movePawn(this.board.getPawn(this.turnationManager.getIdCurrPlayer()), result);
        this.gameView.callChangePositions();
        final Tile currentlySittingTile = this.board.getTileForPawn(this.board.getPawn(currentPlayerId));
        if (currentlySittingTile instanceof Property) {
            final String propertyName = currentlySittingTile.getName();
            this.gameView.displayPropertyContract(this.bank.getTitleDeed(propertyName));
            this.turnActions.clear();
            this.turnActions = Maps.uniqueIndex(this.bank.getApplicableActionsForTitleDeed(currentPlayerId, 
                                    propertyName, 
                                    result.stream().mapToInt(d -> d).sum()),
                                    BankAction::getName);
            this.gameView.showPlayerActions(turnActions.keySet());
        } else if (currentlySittingTile instanceof Special) {
            final Special specialTile = (Special) currentlySittingTile;
            this.gameView.displaySpecialInfo(specialTile);
            executeEffect(specialTile.getEffect());
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
    public void executeAction(final String actionName) {
        try {
            if (!turnActions.containsKey(actionName)) {
                throw new IllegalArgumentException("No action with this name was registered. It is possible that the current"
                + "player has no permission to execute this action on the selected title deed");
            }
            turnActions.get(actionName).executeTransaction();
            final Property currentlySittingProperty = (Property) this.board.getTileForPawn(
                                                        this.board.getPawn(
                                                            this.turnationManager.getIdCurrPlayer()));
            if (actionName == "buy") {
                gameView.callBuyProperty(currentlySittingProperty);
            }
        } catch (final IllegalStateException | IllegalArgumentException e) {
            gameView.displayError(e);
        }
    }
}
