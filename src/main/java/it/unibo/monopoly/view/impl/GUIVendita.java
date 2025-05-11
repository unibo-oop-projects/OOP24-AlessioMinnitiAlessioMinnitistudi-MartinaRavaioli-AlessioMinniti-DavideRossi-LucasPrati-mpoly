package it.unibo.monopoly.view.impl;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.VenditaLogicImpl;
import it.unibo.monopoly.view.api.VenditaLogic;

/**
 * the class presents the property manager frame of the game.
 * where you can look up the values of each of your property 
 * and then decide wether you wwant to sell it or not 
 */

public final class GUIVendita extends JFrame {
    private static final long serialVersionUID = -6218820567019985015L;
    private static final int VGAP = 10;
    private final VenditaLogic logic;

    /**
     * in this constructor the whole GUI is built with all the action listener.
     * @param playerProperties a list of the property possesed by the player 
     * @param width the initial width of the frame
     * @param heigth the initial heigth of the frame
     */

     // TODO al posto di bank viene passato il controller e lo assegni alla logica al posto di usare il costruttore
     //TODO sposta i metodi della logica nel controller
    public GUIVendita(final Player player, final int width, final int heigth, Bank bank) {
        final Border b = BorderFactory.createLineBorder(Color.black);
        logic = new VenditaLogicImpl(bank);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width, heigth);

//  create all the panels
        final JPanel overallPane = new JPanel(new GridLayout(1, 2));
        final BorderLayout righLayout = new BorderLayout();
        righLayout.setVgap(VGAP);
        final JPanel rightPane = new JPanel(righLayout);
        final JPanel actionsPane = new JPanel(new GridLayout(2, 1));
        final JPanel infoPane = new JPanel(new GridLayout(1, 2));
        final GridLayout infoLayout = new GridLayout(5, 1);
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
        final JLabel housesNum = new JLabel("nuber of Houses on the selected property:");
        final JLabel housesNumValue = new JLabel("0");
        final JLabel rent = new JLabel("latest rent from the selected property:");
        final JLabel rentValue = new JLabel("0");
        final JLabel mortage = new JLabel("mortage lending value of the selected property:");
        final JLabel mortageValue = new JLabel("0");
        final JLabel housesCost = new JLabel("value of each house upon selling it:");
        final JLabel housesCostValue = new JLabel("0");
        final JLabel color = new JLabel("color");
        final PropertyColor colorValue = new PropertyColor(Color.BLACK);

// create the sells buttons and user balance label
        final JButton sellHouse = new JButton("sell House");
        sellHouse.setEnabled(false);
        final JButton sellProperty = new JButton("sell Property");
        sellProperty.setEnabled(false);
        final JLabel balance = new JLabel("your balance is: ");
        final JLabel balanceValue = new JLabel(String.valueOf(logic.getPlayerBalance(player)));

// create the Component for the listPane
        final JLabel selectProperty = new JLabel("select the property you want to manage");
        Font f = new Font("gigi", Font.TYPE1_FONT, 20);
        selectProperty.setFont(f);
        final JList<Object> propertiesList = new JList<>(logic.getProperties(player).stream().map(TitleDeed::getName).toArray());
        final JScrollPane propertiesScrollPane = new JScrollPane(propertiesList);
        final JButton exitButton = new JButton("done");


// the listeners for the buttons and the JList

        //exit 
        final ActionListener exitListener = e -> {
            this.dispose();
        };

        //selection of property
        final ListSelectionListener propertySelectionListener = e -> {
            final TitleDeed selectedProperty = logic.getProperty(logic.getProperties(player), propertiesList.getSelectedValue());
            housesCostValue.setText(Integer.toString(selectedProperty.housePrice()));
            mortageValue.setText(Integer.toString(selectedProperty.getMortgagePrice()));
            rentValue.setText(Integer.toString(selectedProperty.getRent(logic.getProperties(player).stream().collect(Collectors.toSet()))));
            housesNumValue.setText(Integer.toString(selectedProperty.houseNum()));
            colorValue.setColor(logic.getPropertyColor(selectedProperty));

            if (logic.areThereHouses(selectedProperty)) {
                sellHouse.setEnabled(true);
                sellProperty.setEnabled(false);
            } else {
                sellHouse.setEnabled(false);
                sellProperty.setEnabled(true);
            }

        };

        // sell house
        final ActionListener sellHouseListener = e -> {
            final TitleDeed property = logic.getProperty(logic.getProperties(player), propertiesList.getSelectedValue());
            if (logic.sellHouse(logic.getProperties(player), propertiesList.getSelectedValue())) {
                final int houses = property.houseNum();
                final PaymentDialog paymentComplete = new PaymentDialog(property.housePrice(), true);
                paymentComplete.setVisible(true);
                balanceValue.setText(""+logic.getPlayerBalance(player));
                if (houses == 0) {
                    housesNumValue.setText(Integer.toString(houses));
                    sellHouse.setEnabled(false);
                    sellProperty.setEnabled(true);
                } else {
                    housesNumValue.setText(Integer.toString(houses));
                }
            } else {
                final PaymentDialog paymentComplete = new PaymentDialog(property.housePrice(), false);
                paymentComplete.setVisible(true);
            }
        };

        //sell property
        final ActionListener sellPropertyListener = e -> {
            final TitleDeed selectedProperty = logic.getProperty(logic.getProperties(player), propertiesList.getSelectedValue());
            if (logic.sellProperty(logic.getProperties(player), selectedProperty)) {
                final PaymentDialog paymentComplete = new PaymentDialog(selectedProperty.getMortgagePrice(), true);
                sellProperty.setEnabled(false);
                paymentComplete.setVisible(true);
                mortageValue.setText("0");
                housesCostValue.setText("0");
                rentValue.setText("0");
                housesNumValue.setText("0");
                colorValue.setColor(Color.BLACK);
                balanceValue.setText(String.valueOf(logic.getPlayerBalance(player)));

                if (logic.getProperties(player).isEmpty()) {
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
        sellHouse.addActionListener(sellHouseListener);
        sellProperty.addActionListener(sellPropertyListener);
        exitButton.addActionListener(exitListener);

// add all the Components to their Panels

        rightPane.add(BorderLayout.SOUTH, exitButton);
        rightPane.add(BorderLayout.CENTER, propertiesScrollPane);
        rightPane.add(BorderLayout.NORTH, selectProperty);

        fieldPane.add(housesNum);
        fieldPane.add(rent);
        fieldPane.add(mortage);
        fieldPane.add(housesCost);
        fieldPane.add(color);

        valuePane.add(housesNumValue);
        valuePane.add(rentValue);
        valuePane.add(mortageValue);
        valuePane.add(housesCostValue);
        valuePane.add(colorValue);

        infoPane.add(fieldPane);
        infoPane.add(valuePane);

        buttonPane.add(sellHouse);
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
