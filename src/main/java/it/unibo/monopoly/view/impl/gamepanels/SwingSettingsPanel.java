package it.unibo.monopoly.view.impl.gamepanels;

import it.unibo.monopoly.controller.api.MainMenuController;
import it.unibo.monopoly.view.api.SettingsPanel;

final class SwingSettingsPanel extends SwingAbstractMainMenuPanel implements SettingsPanel {
    
    private static final long serialVersionUID = 1L;


    public SwingSettingsPanel(MainMenuController controller) {
        super(controller);
    }

    @Override
    public void renderDefaultUI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderDefaultUI'");
    }

}
