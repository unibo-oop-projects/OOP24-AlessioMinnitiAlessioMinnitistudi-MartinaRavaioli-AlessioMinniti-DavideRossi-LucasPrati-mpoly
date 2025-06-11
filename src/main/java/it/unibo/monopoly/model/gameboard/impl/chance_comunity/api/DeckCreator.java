package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import java.io.FileNotFoundException;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * interface for a creator of chence and community chests deck.
 */
public interface DeckCreator {

    /**
     * a method that create a deck of cards based on a file.
     * @param file the file from which the cards effect will be taken
     * @param board to execute some commands 
     * @param bank to execute some commands 
     * @param turnM to execute some commands 
     * @throws FileNotFoundException if the file in the arguments is not valid
     */
    void createDeck(String file, Board board, Bank bank, TurnationManager turnM) throws FileNotFoundException;

}
