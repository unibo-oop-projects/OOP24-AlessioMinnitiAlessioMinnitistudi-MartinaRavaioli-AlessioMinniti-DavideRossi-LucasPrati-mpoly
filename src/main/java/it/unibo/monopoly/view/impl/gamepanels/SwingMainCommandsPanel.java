package it.unibo.monopoly.view.impl.gamepanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.GameController;
import it.unibo.monopoly.view.api.StandardControlsPanel;

final class SwingMainCommandsPanel extends SwingAbstractJPanel implements StandardControlsPanel {

    private static final String DICES_RESULTS_PH = "Risultato dadi:";
    private static final String DICES_TOTAL_PH = "TOTALE TIRO:";
    private static final long serialVersionUID = 1L;


    private final JLabel dicesResultsJLabel;
    private final JLabel dicesTotalJLabel;
    private final JButton throwDicesButton;

    SwingMainCommandsPanel(final GameController controller) {
        this.setLayout(new GridLayout(2, 1));

        //Dices panel UI
        final JPanel dicesJPanel = new JPanel();
        final GridBagLayout dicesPanelLayout = new GridBagLayout();
        dicesJPanel.setLayout(dicesPanelLayout);

        throwDicesButton = new JButton("Lancia i dadi");
        throwDicesButton.addActionListener(e -> {
            controller.throwDices();
            throwDicesButton.setEnabled(false);
        });
        dicesResultsJLabel = new JLabel("Risultato dadi:");
        dicesTotalJLabel = new JLabel("TOTALE:");
        dicesJPanel.add(throwDicesButton);
        dicesJPanel.add(dicesResultsJLabel);
        dicesJPanel.add(dicesTotalJLabel);

        final GridBagConstraints throwDicesButtonConstraints = new GridBagConstraints();
        throwDicesButtonConstraints.weighty = 1.0;
        throwDicesButtonConstraints.fill = GridBagConstraints.BOTH;
        throwDicesButtonConstraints.gridheight = 2;
        dicesPanelLayout.setConstraints(throwDicesButton, throwDicesButtonConstraints);

        final GridBagConstraints dicesResulConstraints = new GridBagConstraints();
        dicesResulConstraints.weightx = 1.0;
        dicesResulConstraints.weighty = 1.0;
        dicesResulConstraints.gridheight = 1;
        dicesResulConstraints.gridwidth = GridBagConstraints.REMAINDER;
        dicesResulConstraints.fill = GridBagConstraints.BOTH;
        dicesPanelLayout.setConstraints(dicesResultsJLabel, dicesResulConstraints);
        dicesPanelLayout.setConstraints(dicesTotalJLabel, dicesResulConstraints);

                //Turn panel UI
        final JPanel turnJPanel = new JPanel();
        final GridBagLayout turnPanelLayout = new GridBagLayout();
        turnJPanel.setLayout(turnPanelLayout);

        final JButton rulesButton = new JButton("?");
        final JButton endTurnButton = new JButton("Termina turno");
        endTurnButton.addActionListener(e -> controller.endTurn());
        rulesButton.addActionListener(e -> controller.loadRules());

        turnJPanel.add(endTurnButton);
        turnJPanel.add(rulesButton);

        final GridBagConstraints fixedButtonsConstraints = new GridBagConstraints();
        fixedButtonsConstraints.fill = GridBagConstraints.BOTH;
        fixedButtonsConstraints.weighty = 1.0;
        turnPanelLayout.setConstraints(rulesButton, fixedButtonsConstraints);

        final GridBagConstraints endTurnButtonConstraints = new GridBagConstraints();
        endTurnButtonConstraints.weighty = 1.0;
        endTurnButtonConstraints.weightx = 1.0;
        endTurnButtonConstraints.fill = GridBagConstraints.BOTH;
        turnPanelLayout.setConstraints(endTurnButton, endTurnButtonConstraints);

        this.add(dicesJPanel);
        this.add(turnJPanel);
    }

    @Override
    public void renderDefaultUI() {
        dicesResultsJLabel.setText(DICES_RESULTS_PH);
        dicesTotalJLabel.setText(DICES_TOTAL_PH);
        throwDicesButton.setEnabled(true);
    }

    @Override
    public void displayDicesResults(final List<Integer> results) {
        final StringBuilder stringBuilder = new StringBuilder();
        results.stream().forEach(i -> {
            stringBuilder.append(i).append(',');
        });
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        dicesResultsJLabel.setText(DICES_RESULTS_PH + stringBuilder.toString());
        dicesTotalJLabel.setText(DICES_TOTAL_PH + Integer.toString(results.stream().mapToInt(i -> i).sum()));
    }
}
