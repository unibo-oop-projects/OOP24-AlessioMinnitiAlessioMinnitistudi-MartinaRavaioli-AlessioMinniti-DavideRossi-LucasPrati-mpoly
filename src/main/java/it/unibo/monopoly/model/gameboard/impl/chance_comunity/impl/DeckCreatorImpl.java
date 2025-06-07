package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.DeckCreator;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;
import it.unibo.monopoly.utils.api.UseFileTxt;
import it.unibo.monopoly.utils.impl.UseFileTxtImpl;
import it.unibo.monopoly.view.impl.MainViewImpl;

/**
 * implementation of deck creator.
 */
public final class DeckCreatorImpl implements DeckCreator {

    @Override
    public ChancheAndCommunityChestDeck createDeck(final String file, final String type, 
                                        final Board board, final Bank bank, final TurnationManager turnM, final MainViewImpl view) {

        final UseFileTxt fi = new UseFileTxtImpl();
        final String fileAsString = fi.loadTextResource(file);
        final ParserOnHyphen paars = new ParserOnHyphen(fileAsString);
        final List<ChanceAndCommunityChestCard> cards = new LinkedList<>();
        final ComplexInterpreter compInt = new ComplexInterpreter(board, bank, view);
        while (paars.hasNesxt()) {
            final String toInterpret = paars.next();
            final Command com = compInt.interpret(toInterpret, board, turnM);
            cards.add(new ChanceAndCommunityChestCard(com));
        } 
        return new ChancheAndCommunityChestDeckImpl(cards, type);
    }

}
