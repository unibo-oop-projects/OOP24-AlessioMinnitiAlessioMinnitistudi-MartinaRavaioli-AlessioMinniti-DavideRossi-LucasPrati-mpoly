package it.unibo.monopoly.controller;


import java.awt.Color;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.BankAccountType;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.bankaccount.BankAccountFactoryImpl;
import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.utils.Configuration;

/**
 * MainMenuLogic implementation.
 */
public final class MainMenuControllerImpl implements MainMenuController {

    private BankAccountType bankAccountType = BankAccountType.CLASSIC;
    private int numPlayers;
    private final int minPlayers;
    private final int maxPlayers;

    /**
     * Creates a new MainMenuController. Based on the given {@link Configuration}
     * @param config a consistent configuration for settings
     */
    public MainMenuControllerImpl(final Configuration config) {
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
            String name = p.getValue();
            Color color = p.getKey();
            id++;
            players.add(PlayerImpl.of(id, name, color));
            accounts.add(BankAccountFactoryImpl.createByType(bankAccountType, id, name));
            // TODO create pawns
        }

        // TODO import tiles from file with a private method

        // TODO create the bankImpl with provided sets of accounts and tiles
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
}
