package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.DeckCreator;

public class DeckCreatorImpl implements DeckCreator {

    @Override
    public ChancheAndCommunityChestDeck createDeck(String type) {
        try {
            FileReader file = new FileReader("command.json");
             
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }

        return null;
    }

}
