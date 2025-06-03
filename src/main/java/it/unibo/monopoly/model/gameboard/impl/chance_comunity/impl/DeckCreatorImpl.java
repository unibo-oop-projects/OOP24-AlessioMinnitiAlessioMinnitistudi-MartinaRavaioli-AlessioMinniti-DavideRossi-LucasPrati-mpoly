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

public class DeckCreatorImpl implements DeckCreator {


    public DeckCreatorImpl(){
    }

    @Override
    public ChancheAndCommunityChestDeck createDeck(String file, String type, Board board, Bank bank, TurnationManager turnM) {

        UseFileTxt fi = new UseFileTxtImpl();
        String fileAsString = fi.loadTextResource(file);
        ParserOnHyphen paars = new ParserOnHyphen(fileAsString);
        List<Chance_CommunityChestCard> cards = new LinkedList<>();
        ComplexInterpreter compInt = new ComplexInterpreter(board, bank);
        while (paars.hasNesxt()) {
            String toInterpret = paars.next();
            Command com = compInt.interpret(toInterpret, board, turnM);
            cards.add(new Chance_CommunityChestCard(com));
        } 
        return new ChancheAndCommunityChestDeckImpl(cards, type);
    }

}
