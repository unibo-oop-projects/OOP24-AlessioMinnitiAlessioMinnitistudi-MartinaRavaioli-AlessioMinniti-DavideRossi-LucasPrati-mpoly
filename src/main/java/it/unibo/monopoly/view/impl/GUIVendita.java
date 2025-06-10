package it.unibo.monopoly.view.impl;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import it.unibo.monopoly.utils.impl.GuiUtils;
/**
 * the class presents the property manager frame of the game.
 * where you can look up the values of each of your property 
 * and then decide wether you wwant to sell it or not 
 */

public final class GUIVendita extends JDialog {
    private static final long serialVersionUID = -6218820567019985015L;
    private static final int VGAP = 10;
    private static final String TITLE_WINDOW = "Property management";
    private static final double PROPORTION = 0.6;


     /**
      * in this constructor the whole GUI is built with all the action listener.
      * @param player the player that wants to manage its properties
      * @param log for game
      * @param bank for the stats
      * @param parentView the GUI parent, used to clear the game board once a property has been sold
      * @param parent the parent frame that owns this dialog and will be blocked while the dialog is visible
      */
    public GUIVendita(
        final Player player,
        final GUIVenditaLogic log,
        final Bank bank,
        final MainViewImpl parentView,
        final Frame parent
    ) {
        final Dimension screenDimension = GuiUtils.getDimensionWindow(PROPORTION, PROPORTION);
        GuiUtils.configureWindow(this,
                                 (int) screenDimension.getWidth(),
                                 (int) screenDimension.getHeight(),
                                 TITLE_WINDOW,
                                 new BorderLayout(),
                                 parent);
        final Border b = BorderFactory.createLineBorder(Color.black);
        final  GUIVenditaLogic logic = log;

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
        final JLabel rent = new JLabel("Latest rent from the selected property:");
        final JLabel rentValue = new JLabel("0");
        final JLabel mortage = new JLabel("Mortage lending value of the selected property:");
        final JLabel mortageValue = new JLabel("0");
        final JLabel color = new JLabel("Color");
        final PropertyColor colorValue = new PropertyColor(Color.BLACK);

// create the sells buttons and user balance label
        final JButton sellProperty = new JButton("Sell Property");
        sellProperty.setEnabled(false);
        final JLabel balance = new JLabel("Your balance is: ");
        final JLabel balanceValue = new JLabel(String.valueOf(logic.getPlayerBalance(player, bank).getBalance()));

// create the Component for the listPane
        final JLabel selectProperty = new JLabel("Select the property you want to manage");
        final JList<Object> propertiesList = new JList<>(logic.getProperties(player, bank)
                                                                        .stream()
                                                                        .map(TitleDeed::getName)
                                                                        .toArray());
        final JScrollPane propertiesScrollPane = new JScrollPane(propertiesList);
        final JButton exitButton = new JButton("Done");


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
            final int auxintrent = selectedProperty.getRent(
                logic.getProperties(player, bank)
                    .stream()
                    .filter(p -> selectedProperty.getGroup().equals(p.getGroup()))
                    .collect(Collectors.toSet()), 1
            );
            String auxrent = String.valueOf(auxintrent);
            if (selectedProperty.getGroup().equals(Group.SOCIETY)) {

                auxrent = auxintrent + " times dice result";
            }
            rentValue.setText(auxrent);
            colorValue.setColor(logic.getPropertyColor(selectedProperty));
            sellProperty.setEnabled(true);
        };
    //sell property
        final ActionListener sellPropertyListener = e -> {
            final boolean statePayment;
            final TitleDeed selectedProperty = logic.getProperty(logic.getProperties(player, bank), 
                                                                propertiesList.getSelectedValue());
            if (logic.sellProperty(selectedProperty, bank)) {
                statePayment = true;
                sellProperty.setEnabled(false);
                mortageValue.setText("0");
                rentValue.setText("0");
                colorValue.setColor(Color.BLACK);
                balanceValue.setText(String.valueOf(logic.getPlayerBalance(player, bank).getBalance()));
                parentView.callClearPanel(selectedProperty.getName());

                if (logic.getProperties(player, bank).isEmpty()) {
                    selectProperty.setText("you have no properties to manage");
                    propertiesList.setVisible(false);
                    propertiesScrollPane.setVisible(false);
                } else {
                    propertiesList.setListData(logic.getProperties(player, bank)
                                                                        .stream()
                                                                        .map(TitleDeed::getName)
                                                                        .toArray());
                }
            } else {
                statePayment = false;
            }

            final String messageState;
            if (statePayment) {
                messageState = "succesfully";
            } else {
                messageState = "unsuccesfully";
            }
            GuiUtils.showInfoMessage(
                this,
                "Payment",
                "The payment of " + selectedProperty.getMortgagePrice() + " has been " + messageState + " made"
            );
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
