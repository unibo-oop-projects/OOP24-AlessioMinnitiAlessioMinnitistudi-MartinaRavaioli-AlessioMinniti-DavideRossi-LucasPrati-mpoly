package it.unibo.monopoly.view.impl;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.monopoly.controller.api.GameController;

class ActionsPanel extends JPanel{

    private static final String CONTRACT_PANEL_PLACEHOLDER = "THE CONTRACT OF THE PROPERTY YOU STEPPED ONTO WILL APPEAR AS SOON AS YOU MAKE A MOVE";

    public ActionsPanel(GameController gameController) {
        buildUI(gameController);        
    }

    private void buildUI(GameController controller) {
        this.add(userInfoPanel(),BorderLayout.NORTH);
        this.add(placeholderContractPanel(),BorderLayout.CENTER);
        this.add(actionsPanel(controller),BorderLayout.WEST);
        this.add(southPanel(controller),BorderLayout.SOUTH);
    }   

    private JPanel userInfoPanel() {
        final JPanel userInfoPanel = new JPanel();
        final JLabel userInfoLabel = new JLabel("userInfo zone");
        userInfoPanel.add(userInfoLabel);
        return userInfoPanel;
    }

    private JPanel placeholderContractPanel() {
        final JPanel contractPanel = new JPanel();
        contractPanel.setLayout(new BorderLayout());
        final JLabel contractPlaceholder = new JLabel(CONTRACT_PANEL_PLACEHOLDER);
        //set style label
        contractPanel.add(contractPlaceholder);
        return contractPanel;
    }

    private JPanel actionsPanel(GameController controller) {
        final JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new GridLayout(2,1));
        final JButton actionButton = new JButton("compra");
        actionButton.addActionListener(e -> controller.buyProperty());
        final JButton propertyViewButton = new JButton("Gestisci proprietà");
        actionsPanel.add(actionButton);
        actionsPanel.add(propertyViewButton);
        return actionsPanel;
    }

    private JPanel balancePanel() {
        final JPanel balancePanel = new JPanel();
        final GridBagLayout balancePanelLayout = new GridBagLayout();
        balancePanel.setLayout(balancePanelLayout);

        final JButton balanceTitleLabel = new JButton("Saldo:");
        final JButton balanceLabel = new JButton("234563256");
        balancePanel.add(balanceTitleLabel);
        balancePanel.add(balanceLabel);

        final GridBagConstraints balanceTitleConstraints = new GridBagConstraints();                
        balancePanelLayout.setConstraints(balanceTitleLabel, balanceTitleConstraints);

        final GridBagConstraints balanceLabelConstraints = new GridBagConstraints();
        balanceLabelConstraints.weightx = 1.0;
        balanceLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        balancePanelLayout.setConstraints(balanceLabel, balanceLabelConstraints);

        return balancePanel;
    }

    private JPanel turnPanel (GameController controller) {
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

    private JPanel southPanel(GameController controller) {        
        final JPanel southControlArea = new JPanel(new GridLayout(3,1));

        southControlArea.add(balancePanel());
        southControlArea.add(turnPanel(controller));
        southControlArea.add(dicesPanel(controller)); 

        return southControlArea;
    }
}
