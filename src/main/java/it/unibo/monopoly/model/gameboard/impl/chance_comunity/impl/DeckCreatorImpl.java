package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.Command;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.DeckCreator;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;

/**
 * implementation of deck creator.
 */
public final class DeckCreatorImpl implements DeckCreator {

    @Override
    public void createDeck(final String file, 
                            final Board board, final Bank bank, 
                            final TurnationManager turnM) {

        final UseFileTxt fi = new UseFileTxtImpl();
        final String fileAsString = fi.loadTextResource(file);
        final ParserOnHyphen paars = new ParserOnHyphen(fileAsString);
        final List<ChanceAndCommunityChestCard> cards = new LinkedList<>();
        final ComplexInterpreter compInt = new ComplexInterpreter(board, bank, turnM);
        while (paars.hasNesxt()) {
            final String toInterpret = paars.next();
            final Command com = compInt.interpret(toInterpret, board, turnM);
            cards.add(new ChanceAndCommunityChestCard(com));
        } 
        board.addDeck(new ChancheAndCommunityChestDeckImpl(cards));
    }

}
