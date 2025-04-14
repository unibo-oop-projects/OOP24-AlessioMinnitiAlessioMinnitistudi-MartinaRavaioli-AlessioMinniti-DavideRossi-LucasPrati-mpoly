package it.unibo.monopoly.view;

import java.util.List;


/**
 * this is the interface of the logic behind the property manager GUI.
 */
public interface VenditaLogic {

    /**
     * this method returns wether there are houses on the property.
     * @param prop the property you want to know if there are houses
     * @return wether there are houses on the property
     */
    boolean areThereHouses(Proprieta prop);

    /**
     * this method removes a house fromm the property. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedValue the property from wich the house will be removed
     * @return wether the payment has been succesful
     */
    boolean sellHouse(List<Proprieta> properties, Object selectedValue);

    /**
     * 
     * @param properties the players property list
     * @param selectedValue the property you want 
     * @return the property 
     */
    Proprieta getProperty(List<Proprieta> properties, Object selectedValue);

    /**
     * this method removes the property fromm the property list. 
     * calls the bank method to deposit the ammount in the pleyers bank accouunt
     * @param properties players properties
     * @param selectedProperty the property you want to sell
     * @return wether the payment has been succesful
     */
    boolean sellProperty(List<Proprieta> properties, Proprieta selectedProperty);

}
