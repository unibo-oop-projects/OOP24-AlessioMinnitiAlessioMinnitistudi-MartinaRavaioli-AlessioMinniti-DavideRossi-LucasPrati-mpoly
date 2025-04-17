package it.unibo.monopoly.controller;


import java.awt.Color;
import java.util.Map;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.impl.PlayerImpl;
import it.unibo.monopoly.utils.Configuration;

/**
 * MainMenuLogic implementation.
 */
public final class MainMenuControllerImpl implements MainMenuController {

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
        for (final var p : playersSetup.entrySet()) {
            final Player player = PlayerImpl.of(p.getValue(), p.getKey());
            System.out.println("Creato: " + player.getName() + " " + player.getColor());
        }
        System.out.println("Gioco pronto! (da implementare)");
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
}
