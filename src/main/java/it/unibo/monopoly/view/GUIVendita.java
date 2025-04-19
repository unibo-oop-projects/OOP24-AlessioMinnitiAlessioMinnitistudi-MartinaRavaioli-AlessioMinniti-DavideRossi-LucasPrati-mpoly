package it.unibo.monopoly.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;

/**
 * the class presents the property manager frame of the game.
 * where you can look up the values of each of your property 
 * and then decide wether you wwant to sell it or not 
 */

public final class GUIVendita extends JFrame {
    private static final long serialVersionUID = -6218820567019985015L;
    private static final int VGAP = 10;
    private final List<Proprieta> properties = new ArrayList<>();
    private final VenditaLogic logic;

    /**
     * in this constructor the whole GUI is built with all the action listener.
     * @param playerProperties a list of the property possesed by the player 
     * @param width the initial width of the frame
     * @param heigth the initial heigth of the frame
     */
    public GUIVendita(final List<Proprieta> playerProperties, final int width, final int heigth) {
        final Border b = BorderFactory.createLineBorder(Color.black);
        logic = new VenditaLogicImpl();
        this.properties.addAll(playerProperties);
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

// set borders for all the panels 
        overallPane.setBorder(b);
        actionsPane.setBorder(b);
        infoPane.setBorder(b);
        buttonPane.setBorder(b);
        fieldPane.setBorder(b);
        valuePane.setBorder(b);

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

// create the sells buttons
        final JButton sellHouse = new JButton("sell House");
        sellHouse.setEnabled(false);
        final JButton sellProperty = new JButton("sell Property");
        sellProperty.setEnabled(false);

// create the Component for the listPane
        final JLabel selectProperty = new JLabel("select the property you want to manage");

        final JList<Object> propertiesList = new JList<>(properties.stream().map(Proprieta::name).toArray());
        final JScrollPane propertiesScrollPane = new JScrollPane(propertiesList);
        final JButton exitButton = new JButton("done");


// the listeners for the buttons and the JList

        //exit 
        final ActionListener exitListener = e -> {
            this.dispose();
        };

        //selection of property
        final ListSelectionListener propertySelectionListener = e -> {
            final Proprieta selectedProperty = logic.getProperty(properties, propertiesList.getSelectedValue());
            housesCostValue.setText(Integer.toString(selectedProperty.housePrice()));
            mortageValue.setText(Integer.toString(selectedProperty.mortage()));
            rentValue.setText(Integer.toString(selectedProperty.latestRent()));
            housesNumValue.setText(Integer.toString(selectedProperty.houseNum()));
            colorValue.setColor(selectedProperty.color());

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
            final Proprieta property = logic.getProperty(properties, propertiesList.getSelectedValue());
            if (logic.sellHouse(properties, propertiesList.getSelectedValue())) {
                final int houses = property.houseNum();
                final PaymentDialog paymentComplete = new PaymentDialog(property.housePrice(), true);
                 paymentComplete.setVisible(true);
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
            final Proprieta selectedProperty = logic.getProperty(properties, propertiesList.getSelectedValue());
            if (logic.sellProperty(properties, selectedProperty)) {
                propertiesList.setListData(properties.stream().map(Proprieta::name).toArray());
                sellProperty.setEnabled(false);
                final PaymentDialog paymentComplete = new PaymentDialog(selectedProperty.mortage(), true);
                paymentComplete.setVisible(true);
                housesCostValue.setText("0");
                mortageValue.setText("0");
                rentValue.setText("0");
                housesNumValue.setText("0");

                if (properties.isEmpty()) {
                    selectProperty.setText("you have no properties to manage");
                    propertiesList.setVisible(false);
                    propertiesScrollPane.setVisible(false);
                }
            } else {
                final PaymentDialog paymentComplete = new PaymentDialog(selectedProperty.mortage(), false);
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

        actionsPane.add(infoPane);
        actionsPane.add(buttonPane);

        overallPane.add(rightPane);
        overallPane.add(actionsPane);

// add everything to the frame and then show it 
        this.getContentPane().add(overallPane);
        this.setVisible(true);
    }

}
