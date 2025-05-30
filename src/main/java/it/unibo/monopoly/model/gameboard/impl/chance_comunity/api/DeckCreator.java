package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import java.io.FileNotFoundException;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public interface DeckCreator {

    ChancheAndCommunityChestDeck createDeck(String type, Board board, Bank bank, TurnationManager turnM) throws FileNotFoundException;

}
