package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Random;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;

/**
 * implementation of ChancheAndCommunityChestDeck.
 */
public final class ChancheAndCommunityChestDeckImpl implements ChancheAndCommunityChestDeck {

    private final List<ChanceAndCommunityChestCard> cardsLis; 
    private final Random r = new Random();

    /**
     * constructor.
     * @param cards the list of cards for this deck
     */
    public ChancheAndCommunityChestDeckImpl(final List<ChanceAndCommunityChestCard> cards) {
        this.cardsLis = List.copyOf(cards);
    }

    @Override
    public ChanceAndCommunityChestCard draw() {
        if (!cardsLis.isEmpty()) {
            return cardsLis.get(r.nextInt(cardsLis.size()));
        } else {
            final BaseCommandFactoryImpl fact = new BaseCommandFactoryImpl();
            return new ChanceAndCommunityChestCard(new ComplexCommand(List.of(new Pair<>(fact.still(), "")), ""));
        }
    }

}
