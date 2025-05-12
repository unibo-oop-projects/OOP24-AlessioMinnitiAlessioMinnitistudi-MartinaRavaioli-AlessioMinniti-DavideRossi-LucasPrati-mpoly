package it.unibo.monopoly.view;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.view.api.VenditaLogic;

/**
 * this is the implementation of the logic behind the property manager GUI. 
 */
public final class VenditaLogicImpl implements VenditaLogic, Serializable {
    static final int NUM = 0;
    private static final long serialVersionUID = -6218820567019985015L;
    private final Bank bank; 

    /**
     * constructor for this class.
     * @param bank
     */
    public VenditaLogicImpl(final Bank bank) {
        this.bank = bank; 
    }

    @Override
    /**
     * this method returns wether there are houses on the property.
     * @param prop the property you want to know if there are houses
     * @return wether there are houses on the property
     */
    public boolean areThereHouses(final TitleDeed prop) {
        return prop.houseNum() > 0;
    }

    @Override
    /**
     * this method removes a house fromm the property. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedValue the property from wich the house will be removed
     * @return wether the payment has been succesful
     */
    public boolean sellHouse(final List<TitleDeed> properties, final Object selectedValue) {
        //manac metodo rossi per far arrivare i soldi al giocatore che vende
        /*final int propInd = getPropertyIndex(properties, selectedValue);
        final int houses = properties.get(propInd).houseNum();
        properties.get(propInd).setHouseNum(houses - 1);*/
        return true;
    }

    @Override
    /**
     * this method removes the property fromm the property list. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedProperty the property you want to sell
     * @return wether the payment has been succesful
     */
    public boolean sellProperty(final TitleDeed selectedProperty) {
        bank.sellTitleDeed(selectedProperty.getName());
        return true;
    }

    @Override
    /**
     * 
     * @param properties the players property list
     * @param selectedValue the property you want 
     * @return the property 
     */
    public TitleDeed getProperty(final List<TitleDeed> properties, final Object selectedValue) {
        final Optional<TitleDeed> selectedPropertyO = properties.stream().filter(p -> p.getName().equals(selectedValue)).findAny();
        TitleDeed selectedProperty = new BaseTitleDeed("null", "null", NUM, null, NUM); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty = selectedPropertyO.get();
        }
        return selectedProperty;
    }

    /**
     * private method to get the index of the selected value from the list.
     * @param properties
     * @param selectedValue
     * @return index of the selected value 
     */
    /*private int getPropertyIndex(final List<TitleDeed> properties, final Object selectedValue) {
        final Optional<TitleDeed> selectedPropertyO = properties.stream().filter(p -> p.getName().equals(selectedValue)).findAny();
        TitleDeed selectedProperty = new BaseTitleDeed("null", "null", NUM, null, NUM); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty = selectedPropertyO.get();
        }
        return properties.indexOf(selectedProperty);
    }*/

    @Override
    public List<TitleDeed> getProperties(Player player) {
        if (bank.getTitleDeedsByOwner(player.getName()).isEmpty()) {
            return List.of();
        }
        return bank.getTitleDeedsByOwner(player.getName()).stream().toList();
    }

    @Override
    public int getPlayerBalance(Player player) {
        return bank.getBankAccount(player.getName()).getBalance();
    }

    @Override
    public Color getPropertyColor(TitleDeed selectedProperty) {
        final String colorS = selectedProperty.getGroup();
        Color color;
        switch (colorS) {
            case "blue":
                color = Color.BLUE;                
                break;
            case "red":
                color = Color.RED;                
                break;
            case "green":
                color = Color.GREEN;                
                break;
            case "yellow":
                color = Color.YELLOW;                
                break;
            case "pink":
                color = Color.PINK;                
                break;
            case "orange":
                color = Color.ORANGE;                
                break;
            case "black":
                color = Color.BLACK;                
                break;
            default:
                color = Color.WHITE;
                break;
        }
        return color;
    }



}
