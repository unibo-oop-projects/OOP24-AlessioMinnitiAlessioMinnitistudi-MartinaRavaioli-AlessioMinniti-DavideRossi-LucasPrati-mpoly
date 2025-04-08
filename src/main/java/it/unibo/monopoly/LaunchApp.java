package it.unibo.monopoly;

import javax.swing.SwingUtilities;

import it.unibo.monopoly.controller.api.MainMenuLogic;
import it.unibo.monopoly.controller.MainMenuLogicImpl;
import it.unibo.monopoly.view.MainMenuGUI;

final class LaunchApp {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainMenuLogic logic = new MainMenuLogicImpl();
            new MainMenuGUI(logic);
        });
    }
}

