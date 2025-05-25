package it.unibo.monopoly.utils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UncheckedIOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.utils.impl.UseFileJsonImpl;


class UseFileJsonImplTest {

    private static final String PATH_TITLE_DEEDS = "debug/cards/debug_title_deeds.json";
    private static final String PATH_TILES = "debug/cards/debug_tiles.json";
    private static final int EXPECTED_NUM_TITLE_DEEDS = 28;
    private static final String EXPECTED_TITLE_DEED = "Boardwalk";
    private static final int EXPECTED_NUM_TILES = 40;
    private static final String EXPECTED_TILE = "Go";

    private final UseFileJsonImpl jsonLoader = new UseFileJsonImpl();


    @Test
    void loadNullTypeThrows() {
        final NullPointerException exception = assertThrows(
            NullPointerException.class,
            () -> jsonLoader.loadJsonList(PATH_TILES, null)
        );
        testMessageFormat(exception.getMessage());
    }

    @Test
    void loadNonExistentPathThrows() {
        final UncheckedIOException exception = assertThrows(
            UncheckedIOException.class,
            () -> jsonLoader.loadJsonList("nonexistent", Tile.class)
        );
        testMessageFormat(exception.getMessage());
    }

    @Test
    void loadExistingTilesReturnsList() {
        final List<Tile> tiles = jsonLoader.loadJsonList(PATH_TILES, Tile.class);
        testCollection(
            tiles,
            EXPECTED_NUM_TILES,
            EXPECTED_TILE,
            Tile::getName
        );
    }

    @Test
    void loadExistingTitleDeedsReturnsSet() {
        final Set<TitleDeed> deeds = jsonLoader.loadTitleDeeds(PATH_TITLE_DEEDS);
        testCollection(
            deeds,
            EXPECTED_NUM_TITLE_DEEDS,
            EXPECTED_TITLE_DEED,
            TitleDeed::getName
        );
    }



    private <T> void testCollection(
        final Collection<T> collection,
        final int expectedNum,
        final String expectedName,
        final Function<T, String> nameExtractor
    ) {
        assertNotNull(collection);
        assertEquals(expectedNum, collection.size(),
                     "Load " + collection.size() + "/" + expectedNum + " elements"
        );
        assertTrue(
            collection.stream()
                      .map(nameExtractor)
                      .anyMatch(expectedName::equalsIgnoreCase),
            "Should contains '" + expectedName + "'"
        );
    }


    private void testMessageFormat(final String message) {
        assertNotNull(message);
        assertFalse(message.isBlank());
    }
}
