package it.unibo.monopoly.view.api;

import java.awt.Component;

/**
 * A generic panel that displays some sort of information
 * and controls to play the game. 
 * The type of content and its meaning depends on 
 * subsequent extensions of this {@code interface}.
 * The graphical components used to build the panel,
 * their type and the way they are arranged depend 
 * entirely on the implementation.
 */
public interface GamePanel {

    /**
     * Clear the panel content and reset it 
     * to a default state.
     * The specific operations that are executed 
     * depend on the implementation. 
     * Generally speaking, invoking this method
     * usually corresponds to a re-render of the panel's ui, displaying what 
     * would be considered a default state. 
     * The components that constitute the panel may change, 
     * as well as their displayed information or their functionality
     */
    void renderDefaultUI();

    /**
     * Returns this panel as a {@link Component}.
     * This might be useful when trying to build a window and 
     * you are using a specific ui library. You might want to
     * reference the panel as a {@link Component}, in order to 
     * be able to embed it in your window (for instance adding the panel to a {@link JFrame}).
     * With this approach, you are still able to call the methods implemented by this {@link GamePanel} {@code interface}
     * (and eventual extensions of this interface) and use the panel based on its logical functionalities; but also 
     * embed it into a user interface by seeing it as a {@link Component}. 
     * @return the underlying panel object that implements this interface, but as a {@link Component}
     */
    Component getPanel();
}
