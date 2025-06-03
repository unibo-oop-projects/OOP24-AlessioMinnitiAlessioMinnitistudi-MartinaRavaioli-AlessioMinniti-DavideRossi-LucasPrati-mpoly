package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Random;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;

public class ChancheAndCommunityChestDeckImpl implements ChancheAndCommunityChestDeck{

    private String type;
    private int ind;
    private final List<ChanceAndCommunityChestCard> cards; 

    public ChancheAndCommunityChestDeckImpl(List<ChanceAndCommunityChestCard> cards, String type){
        this.cards = cards;
        this.type = type;
    }

    @Override
    public ChanceAndCommunityChestCard draw() {
        Random r = new Random();
        int index = r.nextInt(cards.size()); 
        return cards.get(index);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public ChanceAndCommunityChestCard drawInOrder() {
        ind += 1;
        return cards.get((ind -1)%cards.size());
    }

}
