package it.unibo.monopoly.controller.impl;

import java.awt.Color;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.CardFactory;
import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.PawnFactory;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.DeckCreator;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.model.gameboard.impl.CardFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.PawnFactoryImpl;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.DeckCreatorImpl;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.BankAccountFactoryImpl;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.DiceImpl;
import it.unibo.monopoly.model.turnation.impl.ParkablePlayer;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.model.turnation.impl.PositionImpl;
import it.unibo.monopoly.model.turnation.impl.PrisonablePlayer;
import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;
import it.unibo.monopoly.utils.api.UseFileJson;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.UseFileJsonImpl;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;
import it.unibo.monopoly.view.api.MainMenuView;
import it.unibo.monopoly.view.impl.MainMenuViewImpl;


/**
 * {@link MainMenuController} implementation.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    private final Configuration config;
    private final BankAccountFactory bankAccountFactory;
    private final int minPlayers;
    private final int maxPlayers;
    private int numPlayers;
    private BankAccountType bankAccountType = BankAccountType.CLASSIC;
    private MainMenuView view;

    /**
     * Creates a new {@link MainMenuController}. Based on the given {@link Configuration}
     * @param config a consistent configuration for settings
     */
    public MainMenuControllerImpl(final Configuration config) {
        this.config = config;
        this.maxPlayers = config.getMaxPlayer();
        this.minPlayers = config.getMinPlayer();
        this.bankAccountFactory = new BankAccountFactoryImpl(config.getInitBalance());
        this.numPlayers = minPlayers;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickStart(final Map<Color, String> playersSetup) {
        try {
            initGame(playersSetup);

        } catch (final IOException e) {
            view.displayErrorAndExit(
                e.getMessage(),
                "Error loading Json"
            );

        } catch (final UncheckedIOException e) {
            view.displayErrorAndExit(
                e.getMessage(),
                "Error parsing Json"
            );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBankAccountType(final BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickIncrease() {
        increaseNumPlayer();
        view.refreshNumPlayers(numPlayers, alreadyMinPlayers(), alreadyMaxPlayers());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickDecrease() {
        decreaseNumPlayer();
        view.refreshNumPlayers(numPlayers, alreadyMinPlayers(), alreadyMaxPlayers());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickRules() {
        view.displayRules(getRules());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickSettings() {
        view.displaySettingsMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickContinue() {
        view.displaySetupMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickClassicMode() {
        setBankAccountType(BankAccountType.CLASSIC);
        view.refreshSettingsData(getBankAccountType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickInfinityMode() {
        setBankAccountType(BankAccountType.INFINITY);
        view.refreshSettingsData(getBankAccountType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickDone() {
        view.displayMainMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration getConfiguration() {
        return config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        view = new MainMenuViewImpl(this);
        if (Configuration.Builder.isDefault(config)) {
            view.showInfoMessage(
                "Default configuration",
                "Error loading configuration file, default configuration will be used"
            );
        }
    }

    @Override
    public void disposeMainMenu() {
        view.disposeMainMenu();
    }



    /**
     * Initialize all the game.
     * @param playersSetup the players'data, create players according to this
     * @throws IOException if the loading from {@code JSON} failed
     * @throws UncheckedIOException if the parsing from {@code JSON} failed
     * @throws NullPointerException if {@code id}, {@code name} or {@code color} are {@code null}
     */
    private void initGame(final Map<Color, String> playersSetup) throws IOException {
        final List<Player> players = new ArrayList<>();
        final List<Pawn> pawns = new ArrayList<>();
        final List<Tile> tiles = new ArrayList<>();
        final Set<TitleDeed> titleDeeds = new HashSet<>();
        final Set<BankAccount> accounts = new HashSet<>();

        final PawnFactory pawnFactory = new PawnFactoryImpl();
        final UseFileJson importFileJson = new UseFileJsonImpl();

        // create a id for each Player (his Pawn and BankAccount must have the same id)
        int id = 1;
        // create a Player, his Pawn and his BankAccount according to the type chosen
        for (final var p : playersSetup.entrySet()) {
            final String name = p.getValue();
            final Color color = p.getKey();
            players.add(new ParkablePlayer(new PrisonablePlayer(PlayerImpl.of(id, name, color))));
            accounts.add(bankAccountFactory.createBankAccountByType(id, bankAccountType));
            pawns.add(pawnFactory.createBasic(id, new PositionImpl(0), color));
            id++;
        }

        // creation of Bank, Board and TurnationManager
        final Board board = new BoardImpl(List.of(), pawns);
        final BankImpl bank = new BankImpl(accounts, Set.of());
        final TurnationManager turnationManager = new TurnationManagerImpl(
            players,
            new DiceImpl(
                config.getNumDice(),
                config.getSidesPerDie()
            ),
            bank.getBankStateObject()
        );

        // import from json
        final List<CardDTO> dtos = importFileJson.loadJsonList(config.getCardsPath(), CardDTO.class);
        final CardFactory cardFactory = new CardFactoryImpl(board, bank, turnationManager); 
        cardFactory.parse(dtos);
        // populate elements
        titleDeeds.addAll(cardFactory.getDeeds());
        tiles.addAll(cardFactory.getTiles());

        // Add tiles to the board and titleDeeds to the Bank
        tiles.stream().forEach(board::addTile);
        titleDeeds.stream().forEach(bank::addTitleDeed);

        final DeckCreator creator = new DeckCreatorImpl();
        creator.createDeck(config.getDeckPath(), board, bank, turnationManager);

        // start the game
        final var controllerGameManager = new GameControllerImpl(
            board,
            turnationManager,
            config,
            bank
        );
        controllerGameManager.start();
    }


    private boolean alreadyMinPlayers() {
        return numPlayers == minPlayers;
    }


    private boolean alreadyMaxPlayers() {
        return numPlayers == maxPlayers;
    }


    private void decreaseNumPlayer() {
        if (numPlayers > minPlayers) {
            numPlayers--;
        }
    }


    private void increaseNumPlayer() {
        if (numPlayers < maxPlayers) {
            numPlayers++;
        }
    }


    /**
     * Use a {@link UseFileTxt} for getting a {@link String} with all the rules of the game.
     * @return a {@link String} with all the rules of the game
     */
    private String getRules() {
        final UseFileTxt importRules = new UseFileTxtImpl();
        return importRules.loadTextResource(config.getRulesPath());
    }
}
