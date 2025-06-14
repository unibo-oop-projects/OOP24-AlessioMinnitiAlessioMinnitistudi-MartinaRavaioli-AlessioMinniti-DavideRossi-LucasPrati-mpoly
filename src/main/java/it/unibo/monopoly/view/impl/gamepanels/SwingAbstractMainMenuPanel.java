package it.unibo.monopoly.view.impl.gamepanels;

import it.unibo.monopoly.controller.api.MainMenuController;

abstract class SwingAbstractMainMenuPanel extends SwingAbstractJPanel {

    private final MainMenuController controller;

    SwingAbstractMainMenuPanel(final MainMenuController controller) {
        this.controller = controller;
    }

    public MainMenuController getController() {
        return controller;
    }
}
