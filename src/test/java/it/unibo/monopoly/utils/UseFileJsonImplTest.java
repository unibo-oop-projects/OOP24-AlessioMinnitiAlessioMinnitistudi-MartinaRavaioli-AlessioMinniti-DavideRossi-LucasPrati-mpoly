package it.unibo.monopoly.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.utils.impl.UseFileJsonImpl;


class UseFileJsonImplTest {

    private static final String CARDS_JSON = "cards/cards.json";

    private final UseFileJsonImpl loader = new UseFileJsonImpl();

    @Test
    void testLoadCardsJsonReturnsNonEmptyList() {
        final List<CardDTO> cards = loader.loadJsonList(CARDS_JSON, CardDTO.class);
        assertNotNull(cards, "The card list should not be null");
        assertFalse(cards.isEmpty(), "The card list should not be empty");
    }

    @Test
    void testEachCardHasNamePositionAndGroup() {
        final List<CardDTO> cards = loader.loadJsonList(CARDS_JSON, CardDTO.class);
        cards.forEach(card -> {
            assertNotNull(card.getName(),       "Card name should not be null");
            assertNotNull(card.getPosition(),   "Card position should not be null");
            assertNotNull(card.getGroup(),      "Card group should not be null");
        });
    }
}
