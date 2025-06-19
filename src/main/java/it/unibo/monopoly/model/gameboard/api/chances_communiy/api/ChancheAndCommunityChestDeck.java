package it.unibo.monopoly.model.gameboard.api.chances_communiy.api;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.ChanceAndCommunityChestCard;

/**
 * intrerface for a deck of chances and community chests cards.
 */
public interface ChancheAndCommunityChestDeck {

    /**
     * draw a radom card from the deck whitout deleting it.
     * @return the card
     */
    ChanceAndCommunityChestCard draw();

}
