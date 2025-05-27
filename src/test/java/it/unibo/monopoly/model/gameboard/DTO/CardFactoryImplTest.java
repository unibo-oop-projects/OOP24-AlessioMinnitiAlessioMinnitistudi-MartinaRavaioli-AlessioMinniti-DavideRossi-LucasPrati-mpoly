package it.unibo.monopoly.model.gameboard.DTO;

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

    // An empty-bank-account for bypass the constructor validation (it's unused during tests)
    private final BankAccount emptyBankAccount = new SimpleBankAccountImpl(0, "jonny");

    private static final String CARDS_JSON = "debug/cards/debug_cards.json";

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
        assertEquals(40, tiles.size(), "There should be exactly 40 tiles");

        assertNotNull(deeds, "Deeds should not be null");
        assertEquals(28, deeds.size(), "There should be exactly 28 title deeds");
    }
}
