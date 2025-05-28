package it.unibo.monopoly.model.gameboard.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.impl.BoardImpl;
import it.unibo.monopoly.model.gameboard.impl.CardDTO;
import it.unibo.monopoly.model.gameboard.impl.CardFactoryImpl;
import it.unibo.monopoly.model.transactions.api.BankAccount;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BankImpl;
import it.unibo.monopoly.model.transactions.impl.bankaccount.SimpleBankAccountImpl;
import it.unibo.monopoly.utils.impl.UseFileJsonImpl;

class CardFactoryImplTest {

    private static final int EXPECTED_NUM_TITLE_DEEDS = 28;
    private static final int EXPECTED_NUM_TILES = 40;
    private static final String CARDS_JSON = "debug/cards/debug_cards.json";

    // An empty-bank-account for bypass the constructor validation (it's unused during tests)
    private final BankAccount emptyBankAccount = new SimpleBankAccountImpl(0);


    @Test
    void testCardFactoryProducesTilesAndDeedsCorrectly() {
        final List<CardDTO> dtos = new UseFileJsonImpl().loadJsonList(CARDS_JSON, CardDTO.class);
        final CardFactoryImpl factory = new CardFactoryImpl(
            new BoardImpl(),
            new BankImpl(Set.of(emptyBankAccount), Set.of())
        );

        factory.parse(dtos);

        final List<Tile> tiles = factory.getTiles();
        final Set<TitleDeed> deeds = factory.getDeeds();

        assertNotNull(tiles, "Tiles should not be null");
        assertEquals(EXPECTED_NUM_TILES, tiles.size(),
            "There should be exactly " + EXPECTED_NUM_TILES + " tiles"
        );

        assertNotNull(deeds, "Deeds should not be null");
        assertEquals(EXPECTED_NUM_TITLE_DEEDS, deeds.size(),
            "There should be exactly " + EXPECTED_NUM_TITLE_DEEDS + " title deeds"
        );
    }
}
