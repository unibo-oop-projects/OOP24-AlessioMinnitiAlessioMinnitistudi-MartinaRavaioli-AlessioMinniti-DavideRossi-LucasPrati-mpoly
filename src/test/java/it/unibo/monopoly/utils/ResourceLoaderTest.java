package it.unibo.monopoly.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

class ResourceLoaderTest {

    private static final String INVALID_FONT_NAME = "TotallyFakeFont";
    private static final String VALID_FONT_NAME = "ARIAL";
    private static final String VALID_COLOR_NAME = "red";
    private static final String INVALID_COLOR_NAME = "notacolor";
    private static final String INVALID_PATH = "nonexistent";
    private static final String VALID_CONFIG_YML = "debug/config/debug_config.yml";
    private static final String VALID_RULES_TXT = "debug/rules/debug_rules.txt";
    private static final String VALID_TITLE_DEEDS_JSON = "debug/cards/debug_title_deeds.json";
    private static final String VALID_TILES_JSON = "debug/cards/debug_tiles.json";
    private static final int EXPECTED_NUM_TITLE_DEEDS = 28;
    private static final String EXPECTED_TITLE_DEED = "Boardwalk";
    private static final int EXPECTED_NUM_TILES = 40;
    private static final String EXPECTED_TILE = "Go";

    private static final List<String> VALID_FILENAMES = List.of(
        VALID_RULES_TXT,
        VALID_TITLE_DEEDS_JSON,
        VALID_TILES_JSON
    );

    @Test
    void testCheckPathMethod() {
        testCheckFilenameWithExistingFiles();
        testCheckFilenameWithMissingFile();
    }

    @Test
    void testParseColorValid() {
        assertEquals(Color.RED, ResourceLoader.parseColor(VALID_COLOR_NAME),
                     "Color parsing failed for: " + VALID_COLOR_NAME);
    }

    @Test
    void testParseColorInvalidThrows() {
        final IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
            () -> ResourceLoader.parseColor(INVALID_COLOR_NAME),
            "Expected exception for unknown color: " + INVALID_COLOR_NAME
        );
        testExceptionFormat(iae);

        final NullPointerException npe = assertThrows(NullPointerException.class,
            () -> ResourceLoader.parseColor(null),
            "Expected exception for null color"
        );
        testExceptionFormat(npe);
    }

    @Test
    void testGetRequiredReaderValidPath() throws IOException {
        try (var reader = ResourceLoader.getRequiredReader(VALID_RULES_TXT)) {
            assertNotNull(reader, "BufferedReader should not be null for a valid resource path");
            assertTrue(reader.ready(), "Reader should be ready to read content");
            assertFalse(reader.readLine().isBlank(), "The file should contain some non-blank content");
        }
    }

    @Test
    void testGetRequiredReaderInvalidPathThrows() {
        assertThrows(IOException.class,
            () -> ResourceLoader.getRequiredReader(INVALID_PATH),
            "Expected IOException when trying to read a non-existent file"
        );
    }

    @Test
    void testLoadConfigurationFileFallback() {
        final Configuration config = ResourceLoader.loadConfigurationFile(INVALID_PATH);
        assertNotNull(config, "Fallback configuration should not be null");
        // The consistence of the configuration should be check in ConfigurationTest, not here
    }

    @Test
    void testLoadConfigurationFileValid() {
        final Configuration config = ResourceLoader.loadConfigurationFile(VALID_CONFIG_YML); 
        assertNotNull(config);
        assertTrue(config.getMaxPlayer() > 0, "Should load real config values");
    }

    @Test
    void testLoadTitleDeedsReturnsCorrectSize() throws IOException {
        final Set<TitleDeed> titleDeeds = ResourceLoader.loadTitleDeeds(VALID_TITLE_DEEDS_JSON);
        assertEquals(EXPECTED_NUM_TITLE_DEEDS, titleDeeds.size(), 
                    "Expected " + EXPECTED_NUM_TITLE_DEEDS + " title deeds to be loaded");
    }

    @Test
    void testLoadTitleDeedsContainsExpectedTitle() {
        final Set<TitleDeed> titleDeeds = ResourceLoader.loadTitleDeeds(VALID_TITLE_DEEDS_JSON);
        assertTrue(
            titleDeeds.stream().anyMatch(td -> EXPECTED_TITLE_DEED.equalsIgnoreCase(td.getName())),
            "Expected to find a title deed with name '" + EXPECTED_TITLE_DEED + "'"
        );
    }

    @Test
    void testLoadTilesReturnCorrectSize() throws IOException {
        final List<Tile> tiles = ResourceLoader.loadJsonList(VALID_TILES_JSON, Tile.class);
        assertEquals(EXPECTED_NUM_TILES, tiles.size(), 
                    "Expected " + EXPECTED_NUM_TILES + " tiles to be loaded");
    }

    @Test
    void loadTilesContainsExpectedTile() {
        final List<Tile> tiles = ResourceLoader.loadJsonList(VALID_TILES_JSON, Tile.class);
        assertTrue(
            tiles.stream().anyMatch(td -> EXPECTED_TILE.equalsIgnoreCase(td.getName())),
            "Expected to find a tile with name '" + EXPECTED_TILE + "'"
        );
    }

    @Test
    void testLoadTitleDeedsThrowsOnInvalidPath() {
        assertThrows(UncheckedIOException.class,
            () -> ResourceLoader.loadTitleDeeds(INVALID_PATH),
            "Expected UncheckedIOException for invalid path when loading title deeds"
        );
    }

    @Test
    void testLoadJsonListThrowsOnNullClassOrNullPath() {
        assertThrows(NullPointerException.class,
            () -> ResourceLoader.loadJsonList(VALID_TILES_JSON, null),
            "Expected NullPointerException for null Class'type when loading Json"
        );
        assertThrows(NullPointerException.class,
            () -> ResourceLoader.loadJsonList(null, Tile.class),
            "Expected NullPointerException for null path when loading Json"
        );
    }

    @Test
    void testLoadTextResource() {
        assertFileExists(VALID_RULES_TXT);
        final String contents = ResourceLoader.loadTextResource(VALID_RULES_TXT);
        assertNotNull(contents, "Loaded text should not be null");
        assertFalse(contents.isBlank(), "Loaded text should not be blank");
    }

    @Test
    void testIsValidFontName() {
        assertTrue(ResourceLoader.isValidFontName(VALID_FONT_NAME), VALID_FONT_NAME + " should be a valid font");
        assertFalse(ResourceLoader.isValidFontName(INVALID_FONT_NAME), "Fake font should be invalid");
    }



    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }

    private void testCheckFilenameWithExistingFiles() {
        for (final String string : VALID_FILENAMES) {
            assertFileExists(string);
        }
    }

    private void testCheckFilenameWithMissingFile() {
        assertFileNotExists(INVALID_PATH);
    }

    private void assertFileExists(final String filename) {
        assertTrue(ResourceLoader.checkPath(filename),
                   "File should exist: " + filename);
    }

    private void assertFileNotExists(final String filename) {
        assertFalse(ResourceLoader.checkPath(filename),
                    "File should not exist: " + filename);
    }
}
