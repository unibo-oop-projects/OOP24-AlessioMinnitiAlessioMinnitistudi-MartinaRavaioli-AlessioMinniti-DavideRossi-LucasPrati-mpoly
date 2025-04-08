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

public class ActionsPanel extends JFrame{

    public ActionsPanel() {
        buildUI();
        this.pack();
        this.setLocationByPlatform(true);
        
    }

    private JPanel buildDicesPanel() {
        final JPanel dicesPanel = new JPanel();
        dicesPanel.setLayout(new GridLayout(2,1));
        final JButton throwDicesButton = new JButton("Throw dices");
        final JLabel dicesResulButton = new JLabel("Dices result");
        dicesPanel.add(throwDicesButton);
        dicesPanel.add(dicesResulButton);
        return dicesPanel;
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

    private JPanel southPanel() {        
        final JPanel southControlArea = new JPanel();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        southControlArea.setLayout(gridBagLayout);

        final GridBagConstraints balanceTitleConstraints = new GridBagConstraints();                
        final JButton balanceTitleLabel = new JButton("Saldo:");
        gridBagLayout.setConstraints(balanceTitleLabel, balanceTitleConstraints);
        southControlArea.add(balanceTitleLabel);

        final GridBagConstraints balanceLabelConstraints = new GridBagConstraints();
        final JButton balanceLabel = new JButton("234563256");
        balanceLabelConstraints.gridwidth = GridBagConstraints.REMAINDER;
        balanceLabelConstraints.weightx = 1.0;
        balanceLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagLayout.setConstraints(balanceLabel, balanceLabelConstraints);
        southControlArea.add(balanceLabel);

        final GridBagConstraints rulesButtonConstraints = new GridBagConstraints();
        final JButton rulesButton = new JButton("?");
        rulesButtonConstraints.gridwidth = 1;
        rulesButtonConstraints.weightx = 0.0;
        gridBagLayout.setConstraints(rulesButton, rulesButtonConstraints);
        southControlArea.add(rulesButton);

        final GridBagConstraints endTurnButtonConstraints = new GridBagConstraints();
        endTurnButtonConstraints.gridwidth = GridBagConstraints.REMAINDER;
        endTurnButtonConstraints.weightx = 1.0;
        endTurnButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        JButton endTurnButton = new JButton("Termina turno");
        gridBagLayout.setConstraints(endTurnButton, endTurnButtonConstraints);
        southControlArea.add(endTurnButton);

        return southControlArea;
    }

    public void start() {
        this.setVisible(true);
    }



    public static void main(String[] args) {
        ActionsPanel panelView = new ActionsPanel();   
        panelView.start();     
    }
}
