package it.unibo.monopoly.view.impl;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;

import it.unibo.monopoly.controller.api.GUIVenditaLogic;
import it.unibo.monopoly.model.gameboard.impl.Group;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
/**
 * the class presents the property manager frame of the game.
 * where you can look up the values of each of your property 
 * and then decide wether you wwant to sell it or not 
 */

public final class GUIVendita extends JFrame {
    private static final long serialVersionUID = -6218820567019985015L;
    private static final int VGAP = 10;

     /**
      * in this constructor the whole GUI is built with all the action listener.
      * @param player the player that wants to manage its properties
      * @param width of the frame
      * @param heigth of the frame
      * @param log for game
      * @param bank for the stats
      */

    public GUIVendita(final Player player, final int width, final int heigth, final  GUIVenditaLogic log, final Bank bank) {
        final Border b = BorderFactory.createLineBorder(Color.black);
        final  GUIVenditaLogic logic = log;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width, heigth);

//  create all the panels
        final JPanel overallPane = new JPanel(new GridLayout(1, 2));
        final BorderLayout righLayout = new BorderLayout();
        righLayout.setVgap(VGAP);
        final JPanel rightPane = new JPanel(righLayout);
        final JPanel actionsPane = new JPanel(new GridLayout(2, 1));
        final JPanel infoPane = new JPanel(new GridLayout(1, 2));
        final GridLayout infoLayout = new GridLayout(3, 1);
        final JPanel fieldPane = new JPanel(infoLayout);
        final JPanel valuePane = new JPanel(infoLayout);
        final JPanel buttonPane = new JPanel();
        final JPanel balancePane = new JPanel();
        final JPanel rightDownPane = new JPanel(new GridLayout(2, 1));

// set borders for all the panels 
        overallPane.setBorder(b);
        actionsPane.setBorder(b);
        infoPane.setBorder(b);
        rightDownPane.setBorder(b);
        fieldPane.setBorder(b);
        valuePane.setBorder(b);
        buttonPane.setBorder(b);
        balancePane.setBorder(b);

// create all the info labels
        final JLabel rent = new JLabel("latest rent from the selected property:");
        final JLabel rentValue = new JLabel("0");
        final JLabel mortage = new JLabel("mortage lending value of the selected property:");
        final JLabel mortageValue = new JLabel("0");
        final JLabel color = new JLabel("color");
        final PropertyColor colorValue = new PropertyColor(Color.BLACK);

// create the sells buttons and user balance label
        final JButton sellProperty = new JButton("sell Property");
        sellProperty.setEnabled(false);
        final JLabel balance = new JLabel("your balance is: ");
        final JLabel balanceValue = new JLabel(String.valueOf(logic.getPlayerBalance(player, bank).getBalance()));

// create the Component for the listPane
        final JLabel selectProperty = new JLabel("select the property you want to manage");
        final int fontSize = 20;
        final Font f = new Font("gigi", Font.TYPE1_FONT, fontSize);
        selectProperty.setFont(f);
        final JList<Object> propertiesList = new JList<>(logic.getProperties(player, bank)
                                                                        .stream()
                                                                        .map(TitleDeed::getName)
                                                                        .toArray());
        final JScrollPane propertiesScrollPane = new JScrollPane(propertiesList);
        final JButton exitButton = new JButton("done");


// the listeners for the buttons and the JList

        //exit 
        final ActionListener exitListener = e -> {
            this.dispose();
        };

        //selection of property
        final ListSelectionListener propertySelectionListener = e -> {
            final TitleDeed selectedProperty = logic.getProperty(logic.getProperties(player, bank), 
                                                                propertiesList.getSelectedValue());
            mortageValue.setText(Integer.toString(selectedProperty.getMortgagePrice()));
            String auxrent = String.valueOf(selectedProperty.getRent(logic.getProperties(player, bank)
                                                                        .stream()
                                                                        .collect(Collectors.toSet()), 1));
            if (selectedProperty.getGroup().equals(Group.SOCIETY)) {

                auxrent = auxrent + " times dice result";
            }
            rentValue.setText(auxrent);
            colorValue.setColor(logic.getPropertyColor(selectedProperty));
        };
    //sell property
        final ActionListener sellPropertyListener = e -> {
            final TitleDeed selectedProperty = logic.getProperty(logic.getProperties(player, bank), 
                                                                propertiesList.getSelectedValue());
            if (logic.sellProperty(logic.getProperties(player, bank), selectedProperty, bank)) {
                final PaymentDialog paymentComplete = new PaymentDialog(selectedProperty.getMortgagePrice(), true);
                sellProperty.setEnabled(false);
                paymentComplete.setVisible(true);
                mortageValue.setText("0");
                rentValue.setText("0");
                colorValue.setColor(Color.BLACK);
                balanceValue.setText(String.valueOf(logic.getPlayerBalance(player, bank).getBalance()));

                if (logic.getProperties(player, bank).isEmpty()) {
                    selectProperty.setText("you have no properties to manage");
                    propertiesList.setVisible(false);
                    propertiesScrollPane.setVisible(false);
                }
            } else {
                final PaymentDialog paymentComplete = new PaymentDialog(selectedProperty.getMortgagePrice(), false);
                paymentComplete.setVisible(true);
            }

        };

// add the listeners
        propertiesList.addListSelectionListener(propertySelectionListener);
        sellProperty.addActionListener(sellPropertyListener);
        exitButton.addActionListener(exitListener);

// add all the Components to their Panels

        rightPane.add(BorderLayout.SOUTH, exitButton);
        rightPane.add(BorderLayout.CENTER, propertiesScrollPane);
        rightPane.add(BorderLayout.NORTH, selectProperty);

        fieldPane.add(rent);
        fieldPane.add(mortage);
        fieldPane.add(color);

        valuePane.add(rentValue);
        valuePane.add(mortageValue);
        valuePane.add(colorValue);

        infoPane.add(fieldPane);
        infoPane.add(valuePane);

        buttonPane.add(sellProperty);

        balancePane.add(balance);
        balancePane.add(balanceValue);

        rightDownPane.add(buttonPane);
        rightDownPane.add(balancePane);

        actionsPane.add(infoPane);
        actionsPane.add(rightDownPane);

        overallPane.add(rightPane);
        overallPane.add(actionsPane);

// add everything to the frame and then show it 
        this.getContentPane().add(overallPane);
        this.setVisible(true);
    }

}
