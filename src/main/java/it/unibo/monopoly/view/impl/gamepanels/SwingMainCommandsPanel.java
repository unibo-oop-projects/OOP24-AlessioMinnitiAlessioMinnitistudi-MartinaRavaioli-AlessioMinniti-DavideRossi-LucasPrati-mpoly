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

class SwingMainCommandsPanel extends JPanel implements StandardControlsPanel{

    private static final String DICES_RESULTS_PH = "Risultato dadi:";
    private static final String DICES_TOTAL_PH = "TOTALE TIRO:";        

    private final JPanel turnJPanel;
    private final JPanel dicesJPanel;
    private final JLabel dicesResultsJLabel;
    private final JLabel dicesTotalJLabel;

    public SwingMainCommandsPanel (final GameController controller) {    
        this.setLayout(new GridLayout(2,1));    
        turnJPanel = createTurnPanel(controller);
        dicesJPanel = createDicesPanel(controller);
        dicesResultsJLabel = (JLabel) dicesJPanel.getComponent(1);
        dicesTotalJLabel = (JLabel) dicesJPanel.getComponent(2);

        this.add(dicesJPanel);
        this.add(turnJPanel);
    }

    private JPanel createTurnPanel (final GameController controller) {
        final JPanel turnPanel = new JPanel();
        final GridBagLayout turnPanelLayout = new GridBagLayout();
        turnPanel.setLayout(turnPanelLayout);

        final JButton handlePropertiesButton = new JButton("Gestione proprietà");
        final JButton rulesButton = new JButton("?");
        final JButton endTurnButton = new JButton("Termina turno");
        endTurnButton.addActionListener(e -> controller.endTurn());

        turnPanel.add(handlePropertiesButton);
        turnPanel.add(rulesButton);
        turnPanel.add(endTurnButton);

        final GridBagConstraints fixedButtonsConstraints = new GridBagConstraints();
        fixedButtonsConstraints.fill = GridBagConstraints.BOTH;
        fixedButtonsConstraints.weighty = 1.0;
        turnPanelLayout.setConstraints(handlePropertiesButton, fixedButtonsConstraints);
        turnPanelLayout.setConstraints(rulesButton, fixedButtonsConstraints);

        final GridBagConstraints endTurnButtonConstraints = new GridBagConstraints();
        endTurnButtonConstraints.weighty = 1.0;
        endTurnButtonConstraints.weightx = 1.0;
        endTurnButtonConstraints.fill = GridBagConstraints.BOTH;
        turnPanelLayout.setConstraints(endTurnButton, endTurnButtonConstraints);

        return turnPanel;
    }

    private JPanel createDicesPanel(final GameController controller) {
        final JPanel dicesPanel = new JPanel();
        final GridBagLayout dicesPanelLayout = new GridBagLayout();
        dicesPanel.setLayout(dicesPanelLayout);

        final JButton throwDicesButton = new JButton("Lancia i dadi");
        throwDicesButton.addActionListener(e -> controller.throwDices());
        final JLabel dicesResultJLabel = new JLabel("Risultato dadi:");
        final JLabel dicesTotatlJLabel = new JLabel("TOTALE:");
        dicesPanel.add(throwDicesButton);   
        dicesPanel.add(dicesResultJLabel);
        dicesPanel.add(dicesTotatlJLabel);

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
        dicesPanelLayout.setConstraints(dicesResultJLabel, dicesResulConstraints);
        dicesPanelLayout.setConstraints(dicesTotatlJLabel, dicesResulConstraints);

        return dicesPanel;
    }

    @Override
    public void clear() {
        dicesResultsJLabel.setText(DICES_RESULTS_PH);
        dicesTotalJLabel.setText(TOOL_TIP_TEXT_KEY);
    }

    @Override
    public void displayDicesResults(final List<Integer> results) {
        StringBuilder stringBuilder = new StringBuilder();
        results.stream().forEach(i ->{
            stringBuilder.append(i);
            stringBuilder.append(",");
        });
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        dicesResultsJLabel.setText(DICES_RESULTS_PH + stringBuilder.toString());
        dicesTotalJLabel.setText(DICES_TOTAL_PH + Integer.toString(results.stream().mapToInt(i -> i).sum()));
    }
}
