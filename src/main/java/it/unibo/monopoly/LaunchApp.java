package it.unibo.monopoly;

import javax.swing.SwingUtilities;

import it.unibo.monopoly.controller.api.MainMenuLogic;
import it.unibo.monopoly.controller.MainMenuLogicImpl;
import it.unibo.monopoly.view.MainMenuGUI;

/**
 * App entry class.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * App entry point.
     * 
     * @param args launch arguments
     */
    public static void main(final String[] args) {

        SwingUtilities.invokeLater(() -> {
            final MainMenuLogic logic = new MainMenuLogicImpl();
            new MainMenuGUI(logic);
        });
    }
}

