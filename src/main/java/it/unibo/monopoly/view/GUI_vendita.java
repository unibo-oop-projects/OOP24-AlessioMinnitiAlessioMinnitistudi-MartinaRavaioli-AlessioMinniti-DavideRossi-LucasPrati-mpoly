package it.unibo.monopoly.view;


import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;

import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;


public class GUI_vendita extends JFrame {
    private final List<Proprieta> properties = new ArrayList<>();
    private final Vendita_Logic logic;

    public GUI_vendita(final List<Proprieta> player_properties){
        Border b = BorderFactory.createLineBorder(Color.black);
        logic = new Vendita_LogicImpl();
        this.properties.addAll(player_properties);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setLocation(500, 300);

        JPanel overallPane = new JPanel(new GridLayout(1, 2));
        JPanel listPane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel actionsPane = new JPanel(new GridLayout(2, 1));
        JPanel infoPane = new JPanel();
        infoPane.setLayout(new BoxLayout(infoPane, BoxLayout.Y_AXIS));
        JPanel buttonPane = new JPanel();

        overallPane.setBorder(b);
        listPane.setBorder(b);
        actionsPane.setBorder(b);
        infoPane.setBorder(b);
        buttonPane.setBorder(b);

        JLabel housesNum = new JLabel("nuber of Houses on the selected property:");
        housesNum.setAlignmentX(CENTER_ALIGNMENT);
        JLabel housesNumValue = new JLabel("0");
        housesNumValue.setAlignmentX(CENTER_ALIGNMENT);
        JLabel rent = new JLabel("latest rent from the selected property:");
        rent.setAlignmentX(CENTER_ALIGNMENT);
        JLabel rentValue = new JLabel("0");
        rentValue.setAlignmentX(CENTER_ALIGNMENT);
        JLabel mortage = new JLabel("mortage lending value of the selected property:");
        mortage.setAlignmentX(CENTER_ALIGNMENT);
        JLabel mortageValue = new JLabel("0");
        mortageValue.setAlignmentX(CENTER_ALIGNMENT);
        JLabel housesCost = new JLabel("value of each house upon selling it:");
        housesCost.setAlignmentX(CENTER_ALIGNMENT);
        JLabel housesCostValue = new JLabel("0");
        housesCostValue.setAlignmentX(CENTER_ALIGNMENT);

        JButton sellHouse = new JButton("sell House");
        sellHouse.setEnabled(false);
        JButton sellProperty = new JButton("sell Property");
        sellProperty.setEnabled(false);    
        
        JLabel selectProperty = new JLabel("select the property you want to manage");
        JList<Object> propertiesList = new JList<>(properties.stream().map(p->p.name()).toArray());
        JScrollPane propertiesScrollPane = new JScrollPane(propertiesList);

        ListSelectionListener propertySelectionListener = e -> {
            
            Proprieta selectedProperty = logic.getProperty(properties, propertiesList.getSelectedValue());
            housesCostValue.setText(Integer.toString(selectedProperty.house_price()));
            mortageValue.setText(Integer.toString(selectedProperty.mortage()));
            rentValue.setText(Integer.toString(selectedProperty.latest_rent()));
            housesNumValue.setText(Integer.toString(selectedProperty.house_num()));

            logic.setSellButtons(selectedProperty, sellHouse, sellProperty);

        };

        ActionListener sellHouseListener = e-> {
            if (logic.sellHouse(properties, propertiesList.getSelectedValue())) {
                int houses = logic.getProperty(properties, propertiesList.getSelectedValue()).house_num();
                Payment_Dialog paymentComplete = new Payment_Dialog(logic.getProperty(properties, propertiesList.getSelectedValue()).house_price(), true);
                paymentComplete.setVisible(true);
                if (houses==0) {
                    housesNumValue.setText(Integer.toString(houses));
                    sellHouse.setEnabled(false);
                    sellProperty.setEnabled(true);
                }else{
                    housesNumValue.setText(Integer.toString(houses));
                }    
            }else{
                Payment_Dialog paymentComplete = new Payment_Dialog(logic.getProperty(properties, propertiesList.getSelectedValue()).house_price(), false);
                paymentComplete.setVisible(true);
            }
        };  

        ActionListener sellPropertyListener = e -> {
            Proprieta selectedProperty = logic.getProperty(properties, propertiesList.getSelectedValue());
            if (logic.sellProperty(properties, selectedProperty)) {
                propertiesList.setListData(properties.stream().map(p->p.name()).toArray());
                sellProperty.setEnabled(false);
                Payment_Dialog paymentComplete = new Payment_Dialog(selectedProperty.mortage(), true);
                paymentComplete.setVisible(true);
                housesCostValue.setText("0");
                mortageValue.setText("0");
                rentValue.setText("0");
                housesNumValue.setText("0");

                if (properties.size()==0) {
                    selectProperty.setText("you have no properties to manage");
                    propertiesList.setVisible(false);
                    propertiesScrollPane.setVisible(false);
                }
            }else{
                Payment_Dialog paymentComplete = new Payment_Dialog(selectedProperty.mortage(), false);
                paymentComplete.setVisible(true);
            }                      

        };
        
        propertiesList.addListSelectionListener(propertySelectionListener);
        sellHouse.addActionListener(sellHouseListener);
        sellProperty.addActionListener(sellPropertyListener);

        c.gridx=1;
        c.gridy=1;
        c.insets=new Insets(10,0,0,0);
        listPane.add(propertiesScrollPane, c);
        c.gridx=1;
        c.gridy=0;
        c.insets=new Insets(0,0,10,0);
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
