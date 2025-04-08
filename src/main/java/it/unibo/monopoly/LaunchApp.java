package it.unibo.monopoly;

import javax.swing.SwingUtilities;

import it.unibo.monopoly.controller.api.MainMenuLogic;
import it.unibo.monopoly.controller.MainMenuLogicImpl;
import it.unibo.monopoly.view.MainMenuGUI;

/**
 * LaunchApp (Entry point).
 */
public final class LaunchApp {

    private LaunchApp() { }

    public static void main(final String[] args) {

        SwingUtilities.invokeLater(() -> {
            final MainMenuLogic logic = new MainMenuLogicImpl();
            new MainMenuGUI(logic);
        });
    }
}

