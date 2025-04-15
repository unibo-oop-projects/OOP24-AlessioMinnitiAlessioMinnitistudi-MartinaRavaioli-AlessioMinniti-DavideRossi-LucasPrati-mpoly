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
public class MainMenuControllerImpl implements MainMenuController {

    private int numPlayers;
    private final int minPlayers;
    private final int maxPlayers;

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
    public void onClickStart(Map<Color, String> playersSetup) {
        for (final var p : playersSetup.entrySet()) {
            Player player = PlayerImpl.of(p.getValue(), p.getKey());
            System.out.println("Creato: " + player.getName() + " " + player.getColor());
        }
        System.out.println("Gioco pronto! (da implementare)");
    }


    @Override
    public int getNumPlayers() {
        return numPlayers;
    }


    @Override
    public boolean AlreadyMinPlayers() {
        return numPlayers == minPlayers;
    }


    @Override
    public boolean AlreadyMaxPlayers() {
        return numPlayers == maxPlayers;
    }
}
