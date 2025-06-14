package it.unibo.monopoly.view.impl.gamepanels;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.view.api.MenuPanel;

final class SwingMenuPanel extends SwingAbstractMainMenuPanel implements MenuPanel {

    private static final long serialVersionUID = 1L;


    public SwingMenuPanel(MainMenuController controller) {
        super(controller);
    }

    @Override
    public void renderDefaultUI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderDefaultUI'");
    }

}
