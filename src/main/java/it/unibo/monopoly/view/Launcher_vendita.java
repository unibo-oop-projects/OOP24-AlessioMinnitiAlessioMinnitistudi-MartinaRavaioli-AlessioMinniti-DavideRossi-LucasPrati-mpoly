package it.unibo.monopoly.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Launcher_vendita {

    public static void main(final String[] args) throws java.io.IOException {
        final List<Proprieta> properties = new ArrayList<>();
        properties.add(new Proprieta(Color.BLUE ,"parco della vittoria", 50, 300,3,150));
        properties.add(new Proprieta(Color.RED ,"vicolo stretto", 9, 60,0,30));
        new GUIVendita(properties, 500, 300); 
    }

}
