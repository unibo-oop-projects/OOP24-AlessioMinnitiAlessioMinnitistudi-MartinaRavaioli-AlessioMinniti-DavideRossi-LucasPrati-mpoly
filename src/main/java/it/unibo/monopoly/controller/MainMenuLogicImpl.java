package it.unibo.monopoly.controller;


import java.util.List;

import it.unibo.monopoly.controller.api.MainMenuLogic;
import it.unibo.monopoly.utils.PlayerSetup;
import it.unibo.monopoly.view.RulesWindow;

public class MainMenuLogicImpl implements MainMenuLogic {

    public void onClickStart(List<PlayerSetup> players) {
        for (PlayerSetup p : players) {
            System.out.println("Creazione: " + p.name() + " " + p.color());
            // Player player = PlayerFactory.createPlayer(p.name(), p.color());
            // System.out.println("Creato: " + player);

        }
        System.out.println("Gioco pronto! (da implementare)");
    }

    public void onClickShowRules() {
        new RulesWindow();
    }
}
