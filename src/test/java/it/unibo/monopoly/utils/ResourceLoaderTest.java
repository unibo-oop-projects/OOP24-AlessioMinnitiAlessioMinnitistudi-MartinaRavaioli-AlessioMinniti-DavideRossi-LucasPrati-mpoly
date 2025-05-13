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

import it.unibo.monopoly.model.transactions.api.TitleDeed;

class ResourceLoaderTest {

    private static final String INVALID_FONT_NAME = "TotallyFakeFont";
    private static final String VALID_FONT_NAME = "ARIAL";
    private static final String VALID_COLOR_NAME = "red";
    private static final String INVALID_COLOR_NAME = "notacolor";
    private static final String INVALID_FILE = "nonexistent";
    private static final String VALID_RULES_TXT = "rules/rules.txt";
    private static final String VALID_TITLE_DEEDS_JSON = "cards/title_deeds.json";
    private static final String VALID_TILES_JSON = "cards/tiles.json";
    private static final int EXPECTED_NUM_TITLE_DEEDS = 28;
    private static final String EXPECTED_TITLE_DEED = "Boardwalk";

    private static final List<String> VALID_FILENAMES = List.of(
        VALID_RULES_TXT,
        VALID_TITLE_DEEDS_JSON,
        VALID_TILES_JSON
    );

    @Test
    void testCheckFilenameWithExistingFiles() {
        for (final String string : VALID_FILENAMES) {
            assertFileExists(string);
        }
    }

    @Test
    void testCheckFilenameWithMissingFile() {
        assertFileNotExists(INVALID_FILE);
    }

    @Test
    void testParseColorValid() {
        assertEquals(Color.RED, ResourceLoader.parseColor(VALID_COLOR_NAME),
                     "Color parsing failed for: " + VALID_COLOR_NAME);
    }

    @Test
    void testParseColorInvalidThrows() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> ResourceLoader.parseColor(INVALID_COLOR_NAME),
            "Expected exception for unknown color: " + INVALID_COLOR_NAME
        );
        testExceptionFormat(exception);
    }

    @Test
    void testLoadConfigurationFileFallback() {
        final Configuration config = ResourceLoader.loadConfigurationFile(INVALID_FILE);
        assertNotNull(config, "Fallback configuration should not be null");
        // The consistence of the configuration should be check in ConfigurationTest, not here
    }

    @Test
    void testLoadTitleDeedsFromJsonReturnsCorrectSize() throws IOException {
        final Set<TitleDeed> titleDeeds = ResourceLoader.loadTitleDeedsFromJson(VALID_TITLE_DEEDS_JSON);
        assertEquals(EXPECTED_NUM_TITLE_DEEDS, titleDeeds.size(), "Expected " +
                                                              EXPECTED_NUM_TITLE_DEEDS +
                                                              " title deeds to be loaded");
    }

    @Test
    void testLoadTitleDeedsFromJsonContainsExpectedTitle() {
        final Set<TitleDeed> titleDeeds = ResourceLoader.loadTitleDeedsFromJson(VALID_TITLE_DEEDS_JSON);
        assertTrue(
            titleDeeds.stream().anyMatch(td -> td.getName().equalsIgnoreCase(EXPECTED_TITLE_DEED)),
            "Expected to find a title deed with name '" + EXPECTED_TITLE_DEED + "'"
        );
    }

    @Test
    void testLoadTitleDeedsFromJsonThrowsOnInvalidPath() {
        assertThrows(UncheckedIOException.class,
            () -> ResourceLoader.loadTitleDeedsFromJson(INVALID_FILE),
            "Expected UncheckedIOException for invalid path when loading title deeds"
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

    private void assertFileExists(final String filename) {
        assertTrue(ResourceLoader.checkPath(filename),
                   "File not found: " + filename);
    }

    private void assertFileNotExists(final String filename) {
        assertFalse(ResourceLoader.checkPath(filename),
                    "File should not exist: " + filename);
    }
}
