package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.Chance_CommunityChestCard;

public interface ChancheAndCommunityChestDeck {

    Chance_CommunityChestCard draw();

    String getType();

}
