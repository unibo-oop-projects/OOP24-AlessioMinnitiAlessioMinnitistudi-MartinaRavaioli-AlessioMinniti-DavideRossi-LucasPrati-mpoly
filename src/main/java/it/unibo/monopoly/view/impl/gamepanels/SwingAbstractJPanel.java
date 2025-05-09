package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.Component;

import javax.swing.JPanel;

import it.unibo.monopoly.view.api.GamePanel;

abstract class SwingAbstractJPanel extends JPanel implements GamePanel {

    private static final long serialVersionUID = 1L;


    @Override
    public Component getPanel() {
        return this;
    }
}
