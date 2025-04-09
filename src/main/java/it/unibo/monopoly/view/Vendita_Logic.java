package it.unibo.monopoly.view;

import java.util.List;

import javax.swing.JButton;

public interface Vendita_Logic {

    void setSellButtons(Proprieta prop, JButton houseButton, JButton propButton);

    boolean sellHouse(List<Proprieta> properties, Object selectedValue);

    Proprieta getProperty(List<Proprieta> properties, Object selectedValue);

    boolean sellProperty(List<Proprieta> properties, Proprieta selectedProperty);

}
