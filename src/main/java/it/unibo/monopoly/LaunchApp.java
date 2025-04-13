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

    private LaunchApp() { }

    /**
     * App entry point.
     * 
     * @param args launch arguments
     * @throws FileNotFoundException 
     */
    public static void main(final String[] args) throws FileNotFoundException{
        // Configuration config = Configuration.fromYaml("config.yml");
        // MainMenuController controller = new MainMenuControllerImpl(config);
        // MainMenuView view = new MainMenuView(controller, config);
        
        // view.start();
        ///////////////////////
        // final Configuration.Builder configurationBuilder = Configuration.fromYam;
        SwingUtilities.invokeLater(() -> {
            final MainMenuController logic = new MainMenuControllerImpl();
            new MainMenuView(logic);
        });
    }
}

