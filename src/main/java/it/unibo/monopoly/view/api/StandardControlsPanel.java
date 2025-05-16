package it.unibo.monopoly.view.api;

import java.util.List;

/**
 * A panel that displays the main controls to 
 * conduct the game (such as end turn, throw dices...)
 * and information related to these controls (dices result...).
 * Implementation might need a {@link GameController} upon construction.
 */
public interface StandardControlsPanel extends GamePanel {

    /**
     * Displays the results of the dices' throw.
     * @param results a {@link List} of {@code Integer} containing 
     * the numeric results of the dices.
     */
    void displayDicesResults(List<Integer> results);
}
