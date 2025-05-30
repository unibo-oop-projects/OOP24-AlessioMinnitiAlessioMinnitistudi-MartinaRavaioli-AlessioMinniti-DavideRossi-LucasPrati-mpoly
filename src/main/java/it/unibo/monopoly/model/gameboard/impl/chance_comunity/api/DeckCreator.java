package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import java.io.FileNotFoundException;

public interface DeckCreator {

    ChancheAndCommunityChestDeck createDeck(String type) throws FileNotFoundException;

}
