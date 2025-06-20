package it.unibo.monopoly;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import it.unibo.monopoly.controller.impl.MainMenuControllerImpl;
import it.unibo.monopoly.utils.impl.Configuration;
import it.unibo.monopoly.utils.impl.GuiUtils;

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
        GuiUtils.applyGlobalFont(GuiUtils.getFontFromConfiguration(config));

        SwingUtilities.invokeLater(() -> {
            new MainMenuControllerImpl(config).start();
        });
    }
}

