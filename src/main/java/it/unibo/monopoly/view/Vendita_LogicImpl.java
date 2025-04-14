package it.unibo.monopoly.view;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;

public class Vendita_LogicImpl implements Vendita_Logic {

    public void setSellButtons(final Proprieta prop, final JButton houseButton, final JButton propButton) {
        if (prop.house_num()>0) {
            houseButton.setEnabled(true);
            propButton.setEnabled(false);
        }else{
            houseButton.setEnabled(false);
            propButton.setEnabled(true);
        }
    }

    public boolean sellHouse(final List<Proprieta> properties, final Object selectedValue) {
        //manac metodo rossi per far arrivare i soldi al giocatore che vende
        final int propInd = getPropertyIndex(properties, selectedValue);
        final int houses = properties.get(propInd).house_num();
        properties.get(propInd).setHouseNum(houses-1);
        return true;
    }

    
    public boolean sellProperty(final List<Proprieta> properties, final Proprieta selectedProperty) {
        //manac metodo rossi per far arrivare i soldi al giocatore che vende    
        properties.remove(selectedProperty);                   
        return true;
    }


    public Proprieta getProperty(final List<Proprieta> properties, final Object selectedValue) {
        final Optional<Proprieta> selectedPropertyO = properties.stream().filter(p->p.name().equals(selectedValue)).findAny();
        Proprieta selectedProperty=new Proprieta(Color.BLACK, "", 88, 0, 0, 0); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty=selectedPropertyO.get();
        }
        return selectedProperty;
    }

    private int getPropertyIndex(final List<Proprieta> properties, final Object selectedValue) {
        final Optional<Proprieta> selectedPropertyO = properties.stream().filter(p->p.name().equals(selectedValue)).findAny();
        Proprieta selectedProperty=new Proprieta(Color.BLACK, "", 88, 0, 0, 0); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty=selectedPropertyO.get();
        }
        return properties.indexOf(selectedProperty);
    }



}
