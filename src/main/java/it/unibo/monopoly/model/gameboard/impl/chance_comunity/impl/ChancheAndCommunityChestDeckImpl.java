package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Random;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;

/**
 * implementation of ChancheAndCommunityChestDeck.
 */
public final class ChancheAndCommunityChestDeckImpl implements ChancheAndCommunityChestDeck {

    private int ind;
    private final List<ChanceAndCommunityChestCard> cards; 
    private final Random r = new Random();

    /**
     * constructor.
     * @param cards the list of cards for this deck
     */
    public ChancheAndCommunityChestDeckImpl(final List<ChanceAndCommunityChestCard> cards) {
        this.cards = List.copyOf(cards);
    }

    @Override
    public ChanceAndCommunityChestCard draw() {
        if (!cards.isEmpty()) {
            final int index = r.nextInt(cards.size()); 
            return cards.get(index);
        } else {
            final BaseCommandFactoryImpl fact = new BaseCommandFactoryImpl();
            return new ChanceAndCommunityChestCard(new ComplexCommand(List.of(fact.still()), ""));
        }
    }

    @Override
    public ChanceAndCommunityChestCard drawInOrder() {
        ind += 1;
        return cards.get((ind - 1) % cards.size());
    }

}
