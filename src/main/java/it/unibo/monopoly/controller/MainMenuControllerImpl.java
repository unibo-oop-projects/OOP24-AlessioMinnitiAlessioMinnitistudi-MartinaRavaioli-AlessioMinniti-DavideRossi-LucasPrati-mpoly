package it.unibo.monopoly.controller;


import java.awt.Color;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountFactory;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.BankAccountFactoryImpl;
import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.utils.Identifiable;

/**
 * MainMenuLogic implementation.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    private final Configuration config;
    private final BankAccountFactory bankAccountFactory = new BankAccountFactoryImpl();
    private BankAccountType bankAccountType = BankAccountType.CLASSIC;
    private int numPlayers;
    private final int minPlayers;
    private final int maxPlayers;

    /**
     * Creates a new MainMenuController. Based on the given {@link Configuration}
     * @param config a consistent configuration for settings
     */
    public MainMenuControllerImpl(final Configuration config) {
        this.config = config;
        this.maxPlayers = config.getMaxPlayer();
        this.minPlayers = config.getMinPlayer();
        this.numPlayers = minPlayers;
    }
 
    @Override
    public void decreaseNumPlayer() {
        if (numPlayers > minPlayers) {
            numPlayers--;
        }
    }

    @Override
    public void increaseNumPlayer() {
        if (numPlayers < maxPlayers) {
            numPlayers++;
        }
    }

    @Override
    public void onClickStart(final Map<Color, String> playersSetup) {
        // TODO init all the game (Player, Pawn, BankAccount according to the type chosen)
        final Set<Player> players = new HashSet<>();
        final Set<BankAccount> accounts = new HashSet<>();
        // final Set<Pawn> pawns = new HashSet<>();
        final Set<TitleDeed> titleDeeds = new HashSet<>();

        // create a id for each Player (his Pawn and BankAccount must have the same id)
        int id = 0;

        // create a Player, his Pawn and his BankAccount according to the type chosen
        for (final var p : playersSetup.entrySet()) {
            final String name = p.getValue();
            final Color color = p.getKey();
            id++;
            players.add(PlayerImpl.of(id, name, color));
            accounts.add(createBankAccountByType(id, name));
            // TODO create pawns
            // pawns.add();
        }

        // TODO import tiles from file with a private method

        // create the bankImpl with provided sets of accounts and tiles
        final Bank bank = new BankImpl(accounts, titleDeeds);
    }

    @Override
    public int getNumPlayers() {
        return numPlayers;
    }

    @Override
    public boolean alreadyMinPlayers() {
        return numPlayers == minPlayers;
    }

    @Override
    public boolean alreadyMaxPlayers() {
        return numPlayers == maxPlayers;
    }

    @Override
    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    @Override
    public void setBankAccountType(final BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    /**
     * Use {@link BankAccountFactory} to create a new {@link BankAccount} istances according to the {@code bankAccountType}.
     * <p>
     * @param id the {@link Identifiable} representing the {@link BankAccount}
     * @param owner the {@code name} of the {@link Player} that owns the {@link BankAccount} 
     * @return a new istance of {@link BankAccount} according to the {@code bankAccountType}
     * @throws NullPointerException if {@code owner} is {@code null}
     */
    private BankAccount createBankAccountByType(final int id,
                                                final String owner) {
        Objects.requireNonNull(owner);
        final int initialBalance = config.getStarterBalance();
        return switch (bankAccountType) {
            case CLASSIC -> bankAccountFactory.createWithCheck(id, initialBalance, owner);
            case INFINITY  -> bankAccountFactory.createSimple(id, initialBalance, owner);
        };

    }
}
