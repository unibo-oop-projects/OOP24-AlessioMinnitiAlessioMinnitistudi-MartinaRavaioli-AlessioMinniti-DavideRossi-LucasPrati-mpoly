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
import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;
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
    private final GUIVenditaLogic logic;

//  create all the panels
        private final JPanel overallPane = new JPanel(new GridLayout(1, 2));
        private final BorderLayout righLayout = new BorderLayout();
        private final JPanel rightPane = new JPanel(righLayout);
        private final JPanel actionsPane = new JPanel(new GridLayout(2, 1));
        private final JPanel infoPane = new JPanel(new GridLayout(1, 2));
        private final GridLayout infoLayout = new GridLayout(7, 1);
        private final JPanel fieldPane = new JPanel(infoLayout);
        private final JPanel valuePane = new JPanel(infoLayout);
        private final JPanel buttonPane = new JPanel();
        private final JPanel balancePane = new JPanel();
        private final JPanel rightDownPane = new JPanel(new GridLayout(2, 1));

// create all the info labels
        private final JLabel rent = new JLabel("Latest rent:");
        private final JLabel rentValue = new JLabel("0");
        private final JLabel mortage = new JLabel("Mortage lending:");
        private final JLabel mortageValue = new JLabel("0");
        private final JLabel houseNum = new JLabel("Number of houses:");
        private final JLabel houseNumValue = new JLabel("0");
        private final JLabel houseCost = new JLabel("Cost of one house:");
        private final JLabel houseCostValue = new JLabel("0");
        private final JLabel hotelNum = new JLabel("Has hotels?");
        private final JLabel hotelNumValue = new JLabel("no");
        private final JLabel hotelCost = new JLabel("Cost hotel:");
        private final JLabel hotelCostValue = new JLabel("0");
        private final JLabel color = new JLabel("Color");
        private final PropertyColor colorValue = new PropertyColor(Color.BLACK);

        private final JLabel balance = new JLabel("Your balance is: ");
        private final JLabel balanceValue = new JLabel("");

        private final JButton sellProperty = new JButton("Sell Property");
        private final JButton sellHouse = new JButton("Sell House");
        private final JButton sellHotel = new JButton("Sell Hotel");

        //the field is transient because it is the reference to an object.
        private transient TitleDeed selectedDeed;

     /**
      * in this constructor the whole GUI is built with all the action listener.
      * @param player the player that wants to manage its properties
      * @param log for game
      * @param bank for the stats
      * @param board for the stats
      * @param parentView the GUI parent, used to clear the game board once a property has been sold
      * @param parent the parent frame that owns this dialog and will be blocked while the dialog is visible
      */
    public GUIVendita(
        final Player player,
        final GUIVenditaLogic log,
        final Bank bank,
        final Board board,
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
        logic = log;
        righLayout.setVgap(VGAP);

// set borders for all the panels 
        final Border b = BorderFactory.createLineBorder(Color.black);
        overallPane.setBorder(b);
        actionsPane.setBorder(b);
        infoPane.setBorder(b);
        rightDownPane.setBorder(b);
        fieldPane.setBorder(b);
        valuePane.setBorder(b);
        buttonPane.setBorder(b);
        balancePane.setBorder(b);

// set the sells buttons and user balance label
        sellProperty.setEnabled(false);
        sellHouse.setEnabled(false);
        sellHotel.setEnabled(false);
        balanceValue.setText(String.valueOf(logic.getPlayerBalance(player, bank).getBalance()));

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
            if (!propertiesList.isSelectionEmpty()) {
                selectedDeed = logic.getProperty(logic.getProperties(player, bank), 
                                                                    propertiesList.getSelectedValue());

                this.setValues(selectedDeed, player, bank, board);
            }
        };

    //sell property
        final ActionListener sellPropertyListener = e -> {
            final boolean statePayment;
            if (logic.sellProperty(selectedDeed, bank)) {
                statePayment = true;

                parentView.callClearPanel(selectedDeed.getName());
                parentView.displayPlayerInfo(player, bank.getBankAccount(player.getID()));
                this.setNullValues(player, bank);
                sellProperty.setEnabled(false);

                if (logic.getProperties(player, bank).isEmpty()) {
                    sellProperty.setEnabled(false);
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
                "The payment of " + selectedDeed.getMortgagePrice() + " has been " + messageState + " made"
            );
        };

    //sell house
        final ActionListener sellHouseListener = e -> {

            final boolean statePayment;
            final Property selectedBuildableProberty = logic.getBuildableProperty(selectedDeed, board);
            if (logic.sellHouse(selectedBuildableProberty, bank, board)) {
                statePayment = true;

                this.setValues(selectedDeed, player, bank, board);
                parentView.displayPlayerInfo(player, bank.getBankAccount(player.getID()));

                if (selectedBuildableProberty.getNHouses() == 0) {
                    sellHouse.setEnabled(false);
                    sellProperty.setEnabled(true);
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
                "The payment of " + selectedDeed.getHousePrice() + " has been " + messageState + " made"
            );
        };

    //sell hotel
        final ActionListener sellHotelListener = e -> {

            final boolean statePayment;
            final Property selectedBuildableProberty = logic.getBuildableProperty(selectedDeed, board);
            if (logic.sellHotel(selectedBuildableProberty, bank, board)) {
                statePayment = true;

                this.setValues(selectedDeed, player, bank, board);
                parentView.displayPlayerInfo(player, bank.getBankAccount(player.getID()));

                if (selectedBuildableProberty.hasHotel()) {
                    sellHotel.setEnabled(false);
                    sellHouse.setEnabled(true);
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
                "The payment of " + selectedDeed.getHotelPrice() + " has been " + messageState + " made"
            );
        };


// add the listeners
        propertiesList.addListSelectionListener(propertySelectionListener);
        sellProperty.addActionListener(sellPropertyListener);
        sellHouse.addActionListener(sellHouseListener);
        sellHotel.addActionListener(sellHotelListener);
        exitButton.addActionListener(exitListener);

// add all the Components to their Panels

        rightPane.add(BorderLayout.SOUTH, exitButton);
        rightPane.add(BorderLayout.CENTER, propertiesScrollPane);
        rightPane.add(BorderLayout.NORTH, selectProperty);

        fieldPane.add(rent);
        fieldPane.add(mortage);
        fieldPane.add(houseNum);
        fieldPane.add(houseCost);
        fieldPane.add(hotelNum);
        fieldPane.add(hotelCost);
        fieldPane.add(color);

        valuePane.add(rentValue);
        valuePane.add(mortageValue);
        valuePane.add(houseNumValue);
        valuePane.add(houseCostValue);
        valuePane.add(hotelNumValue);
        valuePane.add(hotelCostValue);
        valuePane.add(colorValue);

        infoPane.add(fieldPane);
        infoPane.add(valuePane);

        buttonPane.add(sellProperty);
        buttonPane.add(sellHouse);
        buttonPane.add(sellHotel);

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

    private void setValues(final TitleDeed selectedDeed, final Player player, final Bank bank, final Board board) {
        mortageValue.setText(Integer.toString(selectedDeed.getMortgagePrice()));
        final int auxintrent = selectedDeed.getRent(
            logic.getProperties(player, bank)
                .stream()
                .filter(p -> selectedDeed.getGroup().equals(p.getGroup()))
                .collect(Collectors.toSet()), 1
        );
        String auxrent = String.valueOf(auxintrent);
        if (selectedDeed.getGroup().equals(Group.SOCIETY)) {

            auxrent = auxintrent + " times dice result";
        }
        rentValue.setText(auxrent);
        colorValue.setColor(logic.getPropertyColor(selectedDeed));
        hotelCostValue.setText(String.valueOf(selectedDeed.getHotelPrice()));
        houseCostValue.setText(String.valueOf(selectedDeed.getHousePrice()));
        final Property selectedBuildableProberty = logic.getBuildableProperty(selectedDeed, board);
        houseNumValue.setText(String.valueOf(selectedBuildableProberty.getNHouses()));
        final String hotelsString;
        if (selectedBuildableProberty.hasHotel()) {
            hotelsString = "yes";
        } else {
            hotelsString = "no";
        }
        hotelNumValue.setText(hotelsString);

        balanceValue.setText(String.valueOf(bank.getBankAccount(player.getID()).getBalance()));

        if (selectedBuildableProberty.hasHotel()) {
            sellHotel.setEnabled(true);
            sellHouse.setEnabled(false);
            sellProperty.setEnabled(false);
        } else if (selectedBuildableProberty.getNHouses() > 0) {
            sellHotel.setEnabled(false);
            sellHouse.setEnabled(true);
            sellProperty.setEnabled(false);
        } else {
            sellHotel.setEnabled(false);
            sellHouse.setEnabled(false);
            sellProperty.setEnabled(true);
        }
    }

    private void setNullValues(final Player player, final Bank bank) {
        mortageValue.setText("0");
        rentValue.setText("0");
        colorValue.setColor(Color.BLACK);
        hotelCostValue.setText("0");
        houseCostValue.setText("0");
        houseNumValue.setText("0");
        hotelNumValue.setText("no");

        balanceValue.setText(String.valueOf(bank.getBankAccount(player.getID()).getBalance()));

        sellHotel.setEnabled(false);
        sellHouse.setEnabled(false);
        sellProperty.setEnabled(false);

    }

}
