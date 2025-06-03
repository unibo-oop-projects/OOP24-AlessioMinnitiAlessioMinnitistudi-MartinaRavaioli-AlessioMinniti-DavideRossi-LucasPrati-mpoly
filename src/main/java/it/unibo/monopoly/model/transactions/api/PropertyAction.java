package it.unibo.monopoly.model.transactions.api;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Property;

/**
 * An object encapsulating a game action that has some effect on a {@link Property} or its
 * associated {@link TitleDeed}. 
 * A {@link PropertyAction} has a name that briefly describes the action
 * and a method {@link #executePropertyAction(Board, Bank)} that when invoked
 * executes the incapsulated action.
 * This object can be used to represent macroscopic game actions (place house/hotel, pay rent, but title deed)
 * and game concepts that are specific of the application domain (Monopoly).
 * This actions may span over multiple domains and modify multiple objects such as {@code title deeds, properties...}
 */
public interface PropertyAction {

    /**
     * Get the name of the property action.
     * @return a {@code String} that describes the functionality of the action.
     */
    String getName();

    /**
     * Get a brief description of the property action.
     * @return a brief text describing the functionality of the action.
     */
    String getDescription();

    /**
     * Executes the incapsulated action. 
     * When invoked, it triggers the execution of methods of the
     * {@link Bank} interface (such as {@link Bank#buyTitleDeed(String, int)}, {@link Bank#payRent(int, int, int)})
     * or of the {@link Board} interface
     * @param board the board on which actions will be called
     * @param bank the bank on which actions will be called
     */
    void executePropertyAction(Board board, Bank bank);
}
