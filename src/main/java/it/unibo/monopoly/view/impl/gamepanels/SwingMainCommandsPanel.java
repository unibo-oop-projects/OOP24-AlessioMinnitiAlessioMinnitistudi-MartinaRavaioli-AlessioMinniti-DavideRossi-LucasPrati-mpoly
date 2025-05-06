package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.view.api.StandardControlsPanel;

class SwingMainCommandsPanel extends JPanel implements StandardControlsPanel{

    public SwingMainCommandsPanel (final GameController controller) {    
        this.setLayout(new GridLayout(2,1));    
        this.add(turnPanel(controller));
        this.add(dicesPanel(controller));
    }

    private JPanel turnPanel (final GameController controller) {
        final JPanel turnPanel = new JPanel();
        final GridBagLayout turnPanelLayout = new GridBagLayout();
        turnPanel.setLayout(turnPanelLayout);

        final JButton rulesButton = new JButton("?");
        final JButton endTurnButton = new JButton("Termina turno");
        endTurnButton.addActionListener(e -> controller.endTurn());
        turnPanel.add(rulesButton);
        turnPanel.add(endTurnButton);

        turnPanelLayout.setConstraints(rulesButton, new GridBagConstraints());

        final GridBagConstraints endTurnButtonConstraints = new GridBagConstraints();
        endTurnButtonConstraints.weightx = 1.0;
        endTurnButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        turnPanelLayout.setConstraints(endTurnButton, endTurnButtonConstraints);

        return turnPanel;
    }

    private JPanel dicesPanel(GameController controller) {
        final JPanel dicesPanel = new JPanel();
        final GridBagLayout dicesPanelLayout = new GridBagLayout();
        dicesPanel.setLayout(dicesPanelLayout);

        final JButton throwDicesButton = new JButton("Lancia i dadi");
        throwDicesButton.addActionListener(e -> controller.throwDices());
        final JButton dicesResultJLabel = new JButton("Risultato dadi");
        dicesPanel.add(throwDicesButton);
        dicesPanel.add(dicesResultJLabel);

        final GridBagConstraints throwDicesButtonConstraints = new GridBagConstraints();
        throwDicesButtonConstraints.weightx = 1.0;
        throwDicesButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        dicesPanelLayout.setConstraints(throwDicesButton, throwDicesButtonConstraints);

        final GridBagConstraints dicesResulConstraints = new GridBagConstraints();
        dicesResulConstraints.weightx = 1.0;
        dicesResulConstraints.fill = GridBagConstraints.HORIZONTAL;
        dicesPanelLayout.setConstraints(dicesResultJLabel, dicesResulConstraints);

        return dicesPanel;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public void displayDicesResults(List<Integer> results) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayDicesResults'");
    }
}
