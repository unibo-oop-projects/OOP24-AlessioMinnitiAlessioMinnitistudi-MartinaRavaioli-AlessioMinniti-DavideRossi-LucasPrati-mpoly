package it.unibo.monopoly.view;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

public class GUIVendita extends JFrame {
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
        this.setLocation(width, heigth);

        final JPanel overallPane = new JPanel(new GridLayout(1, 2));
        final JPanel listPane = new JPanel(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        final JPanel actionsPane = new JPanel(new GridLayout(2, 1));
        final JPanel infoPane = new JPanel();
        infoPane.setLayout(new BoxLayout(infoPane, BoxLayout.Y_AXIS));
        final JPanel buttonPane = new JPanel();

        overallPane.setBorder(b);
        listPane.setBorder(b);
        actionsPane.setBorder(b);
        infoPane.setBorder(b);
        buttonPane.setBorder(b);

        final JLabel housesNum = new JLabel("nuber of Houses on the selected property:");
        housesNum.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel housesNumValue = new JLabel("0");
        housesNumValue.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel rent = new JLabel("latest rent from the selected property:");
        rent.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel rentValue = new JLabel("0");
        rentValue.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel mortage = new JLabel("mortage lending value of the selected property:");
        mortage.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel mortageValue = new JLabel("0");
        mortageValue.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel housesCost = new JLabel("value of each house upon selling it:");
        housesCost.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel housesCostValue = new JLabel("0");
        housesCostValue.setAlignmentX(CENTER_ALIGNMENT);

        final JButton sellHouse = new JButton("sell House");
        sellHouse.setEnabled(false);
        final JButton sellProperty = new JButton("sell Property");
        sellProperty.setEnabled(false);
        final JLabel selectProperty = new JLabel("select the property you want to manage");
        final JList<Object> propertiesList = new JList<>(properties.stream().map(p -> p.name()).toArray());
        final JScrollPane propertiesScrollPane = new JScrollPane(propertiesList);

        final ListSelectionListener propertySelectionListener = e -> {
            final Proprieta selectedProperty = logic.getProperty(properties, propertiesList.getSelectedValue());
            housesCostValue.setText(Integer.toString(selectedProperty.housePrice()));
            mortageValue.setText(Integer.toString(selectedProperty.mortage()));
            rentValue.setText(Integer.toString(selectedProperty.latestRent()));
            housesNumValue.setText(Integer.toString(selectedProperty.houseNum()));

            if (logic.areThereHouses(selectedProperty)) {
                sellHouse.setEnabled(true);
                sellProperty.setEnabled(false);
            } else {
                sellHouse.setEnabled(false);
                sellProperty.setEnabled(true);
            }

        };

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

        final ActionListener sellPropertyListener = e -> {
            final Proprieta selectedProperty = logic.getProperty(properties, propertiesList.getSelectedValue());
            if (logic.sellProperty(properties, selectedProperty)) {
                propertiesList.setListData(properties.stream().map(p -> p.name()).toArray());
                sellProperty.setEnabled(false);
                final PaymentDialog paymentComplete = new PaymentDialog(selectedProperty.mortage(), true);
                paymentComplete.setVisible(true);
                housesCostValue.setText("0");
                mortageValue.setText("0");
                rentValue.setText("0");
                housesNumValue.setText("0");

                if (properties.size() == 0) {
                    selectProperty.setText("you have no properties to manage");
                    propertiesList.setVisible(false);
                    propertiesScrollPane.setVisible(false);
                }
            } else {
                final PaymentDialog paymentComplete = new PaymentDialog(selectedProperty.mortage(), false);
                paymentComplete.setVisible(true);
            }

        };

        propertiesList.addListSelectionListener(propertySelectionListener);
        sellHouse.addActionListener(sellHouseListener);
        sellProperty.addActionListener(sellPropertyListener);

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        listPane.add(propertiesScrollPane, c);
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 10, 0);
        listPane.add(selectProperty, c);

        infoPane.add(housesNum);
        infoPane.add(housesNumValue);
        infoPane.add(rent);
        infoPane.add(rentValue);
        infoPane.add(mortage);
        infoPane.add(mortageValue);
        infoPane.add(housesCost);
        infoPane.add(housesCostValue);

        buttonPane.add(sellHouse);
        buttonPane.add(sellProperty);

        actionsPane.add(infoPane);
        actionsPane.add(buttonPane);

        overallPane.add(listPane);
        overallPane.add(actionsPane);

        this.getContentPane().add(overallPane);
        this.setVisible(true);
    }

}
