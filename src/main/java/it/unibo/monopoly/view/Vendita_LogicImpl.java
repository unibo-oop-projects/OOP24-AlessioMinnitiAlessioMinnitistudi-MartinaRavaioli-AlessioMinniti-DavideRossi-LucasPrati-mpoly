package it.unibo.monopoly.view;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;

public class Vendita_LogicImpl implements Vendita_Logic {

    public void setSellButtons(Proprieta prop, JButton houseButton, JButton propButton) {
        if (prop.house_num()>0) {
            houseButton.setEnabled(true);
            propButton.setEnabled(false);
        }else{
            houseButton.setEnabled(false);
            propButton.setEnabled(true);
        }
    }

    public boolean sellHouse(List<Proprieta> properties, Object selectedValue) {
        int propInd = getPropertyIndex(properties, selectedValue);
        int houses = properties.get(propInd).house_num();
        properties.get(propInd).setHouseNum(houses-1);
        return true;
    }

    
    public boolean sellProperty(List<Proprieta> properties, Proprieta selectedProperty) {
        properties.remove(selectedProperty);                   
        return true;
    }
    

    public Proprieta getProperty(List<Proprieta> properties, Object selectedValue) {
        Optional<Proprieta> selectedPropertyO = properties.stream().filter(p->p.name()==selectedValue).findAny();
        Proprieta selectedProperty=new Proprieta(Color.BLACK, "", 88, 0, 0, 0); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty=selectedPropertyO.get();
        }
        return selectedProperty;
    }

    private int getPropertyIndex(List<Proprieta> properties, Object selectedValue) {
        Optional<Proprieta> selectedPropertyO = properties.stream().filter(p->p.name()==selectedValue).findAny();
        Proprieta selectedProperty=new Proprieta(Color.BLACK, "", 88, 0, 0, 0); 
        if (selectedPropertyO.isPresent()) {
            selectedProperty=selectedPropertyO.get();
        }
        return properties.indexOf(selectedProperty);
    }



}
