package it.unibo.monopoly.controller;


import java.util.List;

import it.unibo.monopoly.controller.api.MainMenuLogic;
import it.unibo.monopoly.utils.PlayerSetup;
import it.unibo.monopoly.view.RulesWindow;

/**
 * MainMenuLogic implementation.
 */
public class MainMenuLogicImpl implements MainMenuLogic {

    @Override
    public final void onClickStart(final List<PlayerSetup> players) {
        for (final PlayerSetup p : players) {
            System.out.println("Creazione: " + p.name() + " " + p.color());
            // Player player = PlayerFactory.createPlayer(p.name(), p.color());
            // System.out.println("Creato: " + player);

        }
        System.out.println("Gioco pronto! (da implementare)");
    }

    @Override
    public final void onClickShowRules() {
        new RulesWindow();
    }
}
