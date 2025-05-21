package it.unibo.monopoly;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.view.impl.MainMenuView;

/**
 * App entry class.
 */
public final class LaunchApp {

    private static final String CONFIG_FILE = "configuration/config.yml";

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
            new MainMenuView(config);
        });
    }
}

