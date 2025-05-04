package it.unibo.monopoly.view.impl;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.PlayerPanel;

public class SwingPlayerPanel extends JPanel implements PlayerPanel{

    private static final String PLACEHOLDER = "No player selected";

    public SwingPlayerPanel() {
        clear();
    }

    @Override
    public void clear() {
        final JLabel userInfoLabel = new JLabel(PLACEHOLDER);
        this.add(userInfoLabel);
    }

    @Override
    public void displayPlayer(Player pl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayPlayer'");
    }

}
