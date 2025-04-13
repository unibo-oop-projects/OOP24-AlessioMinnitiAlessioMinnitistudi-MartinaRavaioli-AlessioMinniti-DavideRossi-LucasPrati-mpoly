package it.unibo.monopoly;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.controller.MainMenuControllerImpl;
import it.unibo.monopoly.view.MainMenuView;

/**
 * App entry class.
 */
public final class LaunchApp {

    private static final String CONFIG_FILE = "config.yml";

    private LaunchApp() { }

    /**
     * App entry point.
     * 
     * @param args unused
     * @throws FileNotFoundException 
     */
    public static void main(final String[] args) throws FileNotFoundException {

        final Configuration config = Configuration.configureFromFile(CONFIG_FILE);
        SwingUtilities.invokeLater(() -> {
            final MainMenuController controller = new MainMenuControllerImpl();
            new MainMenuView(config, controller);
        });
    }
}

