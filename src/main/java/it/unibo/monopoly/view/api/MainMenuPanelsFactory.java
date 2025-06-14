package it.unibo.monopoly.view.api;

import it.unibo.monopoly.controller.api.MainMenuController;

/**
 * Abstract factory to create UI components 
 * necessary to display the main menu.
 * All these components comply to view specific interfaces
 * that facilitate the panels update by hiding all the UI
 * managements, which is handled by the panels.
 * The specific UI components and libraries used depend 
 * on the factory implementation.
 */
public interface MainMenuPanelsFactory {

    /**
     * Returns a panel that shows the main menu.
     * @param controller the controller to attach to the action listener of this panel
     * @return an object that implements the {@link MenuPanel} interface
     */
    MenuPanel menuPanel(MainMenuController controller);

    /**
     * Returns a panel that shows the setup menu.
     * @param controller the controller to attach to the action listener of this panel
     * @return an object that implements the {@link SetupPanel} interface
     */
    SetupPanel setupPanel(MainMenuController controller);

    /**
     * Returns a panel that shows the setup menu.
     * @param controller the controller to attach to the action listener of this panel
     * @return an object that implements the {@link SettingsPanel} interface
     */
    SettingsPanel settingsPanel(MainMenuController controller);

}
