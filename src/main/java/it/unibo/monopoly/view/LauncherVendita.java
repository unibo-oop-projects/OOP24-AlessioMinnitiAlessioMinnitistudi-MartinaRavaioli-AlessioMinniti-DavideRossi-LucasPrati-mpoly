package it.unibo.monopoly.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * test class for the property manager frame.
 */

public final class LauncherVendita {

    /**
     * this private constructor's use is to preent form instancing an object of this class.
     */
    private LauncherVendita() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * this is the launcher of the property manager frame.
     * @param args
    */
    public static void main(final String[] args) throws java.io.IOException {
        final int num = 50;
        final int width = 700;
        final int heigth = 500;
        final List<Proprieta> properties = new ArrayList<>();
        properties.add(new Proprieta(Color.BLUE, "parco della vittoria", num, num, num, num));
        properties.add(new Proprieta(Color.RED, "vicolo stretto", num, num, num, num));
        new GUIVendita(properties, width, heigth); 
    }

}
