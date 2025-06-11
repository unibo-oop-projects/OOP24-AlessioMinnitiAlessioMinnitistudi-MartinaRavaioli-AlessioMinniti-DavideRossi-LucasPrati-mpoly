package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Random;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;

/**
 * implementation of ChancheAndCommunityChestDeck.
 */
public final class ChancheAndCommunityChestDeckImpl implements ChancheAndCommunityChestDeck {

    private final List<ChanceAndCommunityChestCard> cardsLis; 
    //private final Random r = new Random();
    private int index = -1;

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
            //final int index = r.nextInt(cardsLis.size()+1);
            System.out.println(index);
            index=index+1;
            System.out.println(cardsLis.get(index).getDescription());
            return cardsLis.get(index);
        } else {
            final BaseCommandFactoryImpl fact = new BaseCommandFactoryImpl();
            return new ChanceAndCommunityChestCard(new ComplexCommand(List.of(new Pair<>(fact.still(), "")), ""));
        }
    }

}
