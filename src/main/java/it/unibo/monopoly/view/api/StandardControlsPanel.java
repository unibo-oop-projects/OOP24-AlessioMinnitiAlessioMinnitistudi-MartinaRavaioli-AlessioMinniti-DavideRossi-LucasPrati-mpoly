package it.unibo.monopoly.view.api;

import java.util.List;

/**
 * A panel that displays the main controls to 
 * conduct the game.
 */
public interface StandardControlsPanel extends InfoPanel {

    /**
     * Displays the results of the dices' throw.
     * @param results a {@link List} of {@code Integer} containing 
     * the numeric results of the dices.
     */
    void displayDicesResults(List<Integer> results);
}
