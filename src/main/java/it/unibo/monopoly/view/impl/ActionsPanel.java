package it.unibo.monopoly.view.impl;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActionsPanel extends JFrame{



    public ActionsPanel() {
        this.getContentPane().setLayout(new BorderLayout());

        final JPanel throwPanel = new JPanel(new GridBagLayout());

        final JButton throwDicesButton = new JButton("Throw Dices");
        final JLabel dicesResult = new JLabel("5");

        throwPanel.add(throwDicesButton,BorderLayout.NORTH);
        throwPanel.add(dicesResult,BorderLayout.SOUTH);

        final JPanel userInfo = new JPanel(new BorderLayout());

        this.getContentPane().add(throwPanel,BorderLayout.NORTH);

        this.pack();
        this.setLocationByPlatform(true);
        
    }

    public void start() {
        this.setVisible(true);
    }



    public static void main(String[] args) {
        ActionsPanel panelView = new ActionsPanel();   
        panelView.start();     
    }
}
