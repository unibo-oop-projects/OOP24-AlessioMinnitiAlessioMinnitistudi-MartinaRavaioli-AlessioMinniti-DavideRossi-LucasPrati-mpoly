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

class ActionsPanel extends JFrame{

    public ActionsPanel() {
        buildUI();
        this.pack();
        this.setLocationByPlatform(true);
        
    }

    private JPanel userInfoPanel() {
        final JPanel userInfoPanel = new JPanel();
        final JLabel userInfoLabel = new JLabel("userInfo zone");
        userInfoPanel.add(userInfoLabel);
        return userInfoPanel;
    }

    private JPanel tilePanel() {
        final JPanel tilePanel = new JPanel();
        final JLabel tilePlaceholder = new JLabel("HERE GOES THE TILE");
        tilePanel.add(tilePlaceholder);
        return tilePanel;
    }

    private JPanel actionsPanel() {
        final JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new GridLayout(2,1));
        final JButton actionButton = new JButton("compra");
        final JButton propertyViewButton = new JButton("Gestisci proprietà");
        actionsPanel.add(actionButton);
        actionsPanel.add(propertyViewButton);
        return actionsPanel;
    }

    private void buildUI() {
        this.getContentPane().add(userInfoPanel(),BorderLayout.NORTH);
        this.getContentPane().add(tilePanel(),BorderLayout.CENTER);
        this.getContentPane().add(actionsPanel(),BorderLayout.WEST);
        this.getContentPane().add(southPanel(),BorderLayout.SOUTH);
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

    private JPanel turnPanel () {
        final JPanel turnPanel = new JPanel();
        final GridBagLayout turnPanelLayout = new GridBagLayout();
        turnPanel.setLayout(turnPanelLayout);

        final JButton rulesButton = new JButton("?");
        final JButton endTurnButton = new JButton("Termina turno");
        turnPanel.add(rulesButton);
        turnPanel.add(endTurnButton);

        turnPanelLayout.setConstraints(rulesButton, new GridBagConstraints());

        final GridBagConstraints endTurnButtonConstraints = new GridBagConstraints();
        endTurnButtonConstraints.weightx = 1.0;
        endTurnButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        turnPanelLayout.setConstraints(endTurnButton, endTurnButtonConstraints);

        return turnPanel;
    }

    private JPanel dicesPanel() {
        final JPanel dicesPanel = new JPanel();
        final GridBagLayout dicesPanelLayout = new GridBagLayout();
        dicesPanel.setLayout(dicesPanelLayout);

        final JButton throwDicesButton = new JButton("Lancia i dadi");
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

    private JPanel southPanel() {        
        final JPanel southControlArea = new JPanel(new GridLayout(3,1));

        southControlArea.add(balancePanel());
        southControlArea.add(turnPanel());
        southControlArea.add(dicesPanel()); 

        return southControlArea;
    }

    private void start() {
        this.setVisible(true);
    }



    public static void main(String[] args) {
        ActionsPanel panelView = new ActionsPanel();   
        panelView.start();     
    }
}
