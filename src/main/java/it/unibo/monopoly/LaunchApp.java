package it.unibo.monopoly;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import it.unibo.monopoly.controller.api.MainMenuLogic;
import it.unibo.monopoly.utils.Configuration;
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
     * @throws FileNotFoundException 
     */
    public static void main(final String[] args) throws FileNotFoundException{
        // Configuration config = Configuration.fromYaml("config.yml");
        // MainMenuLogic controller = new MainMenuLogicImpl(config);
        // MainMenuGUI view = new MainMenuGUI(controller, config);
        
        // view.start();
        ///////////////////////
        SwingUtilities.invokeLater(() -> {
            final MainMenuLogic logic = new MainMenuLogicImpl();
            new MainMenuGUI(logic);
        });
    }
}

