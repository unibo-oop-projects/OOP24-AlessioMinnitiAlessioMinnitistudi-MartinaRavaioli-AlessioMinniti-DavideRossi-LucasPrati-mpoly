package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.DeckCreator;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public class DeckCreatorImpl implements DeckCreator {


    public DeckCreatorImpl(){
    }

    @Override
    public ChancheAndCommunityChestDeck createDeck(String type, Board board, Bank bank, TurnationManager turnM) {
        try {
            FileReader file = new FileReader("command.txt");
            ParserOnHyphen paars = new ParserOnHyphen(file);
            List<Chance_CommunityChestCard> cards = new LinkedList<>();
            while (paars.hasNesxt()) {
                String toInterpret = paars.next();
                ComplexInterpreter compInt = new ComplexInterpreter(board, bank);
                Command com = compInt.interpret(toInterpret, board, turnM);
                cards.add(new Chance_CommunityChestCard(toInterpret, com));
            } 
            return new ChancheAndCommunityChestDeckImpl(cards, type);
        } catch (FileNotFoundException e) {
            System.out.println("not found");
            return new ChancheAndCommunityChestDeckImpl(new LinkedList<>(), "null");
        }

    }

}
