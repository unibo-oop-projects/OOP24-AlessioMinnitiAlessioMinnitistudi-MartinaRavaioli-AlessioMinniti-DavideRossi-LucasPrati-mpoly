package it.unibo.monopoly.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.TileImpl;
// import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
// import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.BankAccountFactoryImpl;
import it.unibo.monopoly.model.turnation.api.Player;
// import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
// import it.unibo.monopoly.model.turnation.impl.TurnationManagerImpl;
import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.utils.Identifiable;
import it.unibo.monopoly.utils.ResourceLoader;


/**
 * {@link MainMenuController} implementation.
 */
public final class MainMenuControllerImpl  implements MainMenuController {

    private final Configuration config;
    private final BankAccountFactory bankAccountFactory;
    private BankAccountType bankAccountType = BankAccountType.CLASSIC;
    private int numPlayers;
    private final int minPlayers;
    private final int maxPlayers;

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
    public void decreaseNumPlayer() {
        if (numPlayers > minPlayers) {
            numPlayers--;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseNumPlayer() {
        if (numPlayers < maxPlayers) {
            numPlayers++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClickStart(final Map<Color, String> playersSetup) throws IOException {
        // init all the game (Player, Pawn, BankAccount according to the type chosen)
        final List<Player> players = new ArrayList<>();
        final Set<BankAccount> accounts = new HashSet<>();
        // final List<Pawn> pawns = new ArrayList<>();
        final Set<TitleDeed> titleDeeds = new HashSet<>();
        final List<Tile> tiles = new ArrayList<>();

        // create a id for each Player (his Pawn and BankAccount must have the same id)
        int id = 0;

        // create a Player, his Pawn and his BankAccount according to the type chosen
        for (final var p : playersSetup.entrySet()) {
            final String name = p.getValue();
            final Color color = p.getKey();
            int index = id;     // used for collocate players and pawns in the right order, following the ids
            id++;
            players.add(index, PlayerImpl.of(id, name, color));
            accounts.add(createBankAccountByType(id, name));
            // TODO create pawns
            // pawns.add(index, PawnsImpl.of(id, color));
        }

        // import titledeeds and tiles from json
        titleDeeds.addAll(Set.copyOf(ResourceLoader.loadJsonList(config.getTitleDeedsPath(), BaseTitleDeed.class)));
        tiles.addAll(List.copyOf(ResourceLoader.loadJsonList(config.getTilesPath(), TileImpl.class)));

        // create the bankImpl with provided sets of accounts and titles
        // final Bank bank = new BankImpl(accounts, titleDeeds);
        // create Board
        // final Board board = new BoardImpl(List<Tile>, pawns);
        // create TurnationManager
        // final TurnationManager turnationManager = new TurnationManagerImpl(config, players);
         

        // TODO launch a new GUI for the game and put all these data to it
        // launch C_Game_Manager(config, board) //alessio
        // launch controller di davide (config, bank, )
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
    public boolean alreadyMinPlayers() {
        return numPlayers == minPlayers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean alreadyMaxPlayers() {
        return numPlayers == maxPlayers;
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
    public String getRules() {
        return ResourceLoader.loadTextResource(config.getRulesPath());
    }


    /**
     * Use {@link BankAccountFactory} to create a new {@link BankAccount} istances according to the {@code bankAccountType}.
     * 
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param owner the {@code name} of the {@link Player} that owns the {@link BankAccount} 
     * @return a new istance of {@link BankAccount} according to the {@code bankAccountType}
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    private BankAccount createBankAccountByType(final int id,
                                                final String owner) {
        Objects.requireNonNull(owner);
        return switch (bankAccountType) {
            case CLASSIC -> bankAccountFactory.createWithCheck(id, owner,
                                                               account -> account.getBalance() > 0);
            case INFINITY  -> bankAccountFactory.createSimple(id, owner);
        };
    }
}
