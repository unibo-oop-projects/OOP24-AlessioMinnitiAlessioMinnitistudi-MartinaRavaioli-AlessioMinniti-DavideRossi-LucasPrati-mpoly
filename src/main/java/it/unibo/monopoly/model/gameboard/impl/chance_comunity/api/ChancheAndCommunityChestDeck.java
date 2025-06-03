package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.Chance_CommunityChestCard;

public interface ChancheAndCommunityChestDeck {

    /**
     * draw a card from the deck.
     * @return the card
     */
    Chance_CommunityChestCard draw();

    /**
     * gets the type of deck, chances or community chest.
     * @return the type
     */
    String getType();
}
