package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ChancheAndCommunityChestDeck;

public class ChancheAndCommunityChestDeckImpl implements ChancheAndCommunityChestDeck{

    private String type;

    private final List<Chance_CommunityChestCard> cards; 

    public ChancheAndCommunityChestDeckImpl(List<Chance_CommunityChestCard> cards, String type){
        this.cards = cards;
        this.type = type;
    }

    @Override
    public Chance_CommunityChestCard draw() {
        int index = (int)Math.random()*100 % cards.size(); 
        return cards.get(index);
    }

    @Override
    public String getType() {
        return this.type;
    }

}
