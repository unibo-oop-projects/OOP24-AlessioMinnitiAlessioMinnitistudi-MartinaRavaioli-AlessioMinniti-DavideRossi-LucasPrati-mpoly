package it.unibo.monopoly.view;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

/**
 * this is the implementation of the logic behind the property manager GUI. 
 */
public class VenditaLogicImpl implements VenditaLogic {
    private final int num = 0;

    @Override
    /**
     * this method returns wether there are houses on the property.
     * @param prop the property you want to know if there are houses
     * @return wether there are houses on the property
     */
    public boolean areThereHouses(final Proprieta prop) {
        if (prop.houseNum() > 0) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * this method removes a house fromm the property. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedValue the property from wich the house will be removed
     * @return wether the payment has been succesful
     */
    public boolean sellHouse(final List<Proprieta> properties, final Object selectedValue) {
        //manac metodo rossi per far arrivare i soldi al giocatore che vende
        final int propInd = getPropertyIndex(properties, selectedValue);
        final int houses = properties.get(propInd).houseNum();
        properties.get(propInd).setHouseNum(houses - 1);
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
    public boolean sellProperty(final List<Proprieta> properties, final Proprieta selectedProperty) {
        //manac metodo rossi per far arrivare i soldi al giocatore che vende
        properties.remove(selectedProperty);
        return true;
    }

    @Override
    /**
     * 
     * @param properties the players property list
     * @param selectedValue the property you want 
     * @return the property 
     */
    public Proprieta getProperty(final List<Proprieta> properties, final Object selectedValue) {
        final Optional<Proprieta> selectedPropertyO = properties.stream().filter(p -> p.name().equals(selectedValue)).findAny();
        Proprieta selectedProperty = new Proprieta(Color.BLACK, "", num, num, num, num); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty = selectedPropertyO.get();
        }
        return selectedProperty;
    }

    /**
     * private method to get the index of the selected value from the list.
     * @param properties
     * @param selectedValue
     * @return
     */
    private int getPropertyIndex(final List<Proprieta> properties, final Object selectedValue) {
        final Optional<Proprieta> selectedPropertyO = properties.stream().filter(p -> p.name().equals(selectedValue)).findAny();
        Proprieta selectedProperty = new Proprieta(Color.BLACK, "", num, num, num, num); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty = selectedPropertyO.get();
        }
        return properties.indexOf(selectedProperty);
    }



}
