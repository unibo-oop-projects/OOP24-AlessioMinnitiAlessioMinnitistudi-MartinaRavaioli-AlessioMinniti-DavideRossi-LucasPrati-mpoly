package it.unibo.monopoly.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigurationTest {

    private static final String MESSAGE_INVALID_CONFIG = "Invalid configuration should not be consistent: ";
    private static final int VALID_MIN = 2;
    private static final int VALID_MAX = 4;
    private static final int VALID_NUM_DICE = 2;
    private static final int VALID_SIDES_PER_DIE = 6;
    private static final String VALID_FONT = "ARIAL"; // should be available on most systems
    private static final int VALID_BIG_FONT = 24;
    private static final int VALID_SMALL_FONT = 16;
    private static final int VALID_STARTER_BALANCE = 1500;
    private static final String VALID_RULES_FILENAME = "rules/rules.txt";
    private static final String VALID_CARDS_FILENAME = "cards/monopoly_cards.json";
    private static final List<String> VALID_FILENAMES = List.of(
        VALID_RULES_FILENAME,
        VALID_CARDS_FILENAME
    );
    private static final List<Color> VALID_COLORS = List.of(
        Color.RED,
        Color.BLUE,
        Color.ORANGE,
        Color.GREEN,
        Color.MAGENTA,
        Color.CYAN,
        Color.YELLOW,
        Color.BLACK,
        Color.LIGHT_GRAY,
        Color.PINK,
        Color.DARK_GRAY,
        Color.GRAY,
        Color.WHITE
    );

    private Configuration.Builder builder;

    @BeforeEach
    void initBuilder() {
        builder = new Configuration.Builder()
                .withMin(VALID_MIN)
                .withMax(VALID_MAX)
                .withNumDice(VALID_NUM_DICE)
                .withSidesPerDie(VALID_SIDES_PER_DIE)
                .withFontName(VALID_FONT)
                .withBigFont(VALID_BIG_FONT)
                .withSmallFont(VALID_SMALL_FONT)
                .withInitBalance(VALID_STARTER_BALANCE)
                .withRulesFilename(VALID_RULES_FILENAME)
                .withCardsFilename(VALID_CARDS_FILENAME)
                .withColors(VALID_COLORS);
    }

    @Test
    void buildValidConfiguration() {
        checkFilenames();
        final Configuration config = builder.build();
        assertNotNull(config);
        assertTrue(config.isConsistent());
        assertEquals(VALID_MIN, config.getMinPlayer());
        assertEquals(VALID_MAX, config.getMaxPlayer());
        assertEquals(VALID_NUM_DICE, config.getNumDice());
        assertEquals(VALID_SIDES_PER_DIE, config.getSidesPerDie());
        assertEquals(VALID_FONT, config.getFontName());
        assertEquals(VALID_BIG_FONT, config.getBigFont());
        assertEquals(VALID_SMALL_FONT, config.getSmallFont());
        assertEquals(VALID_STARTER_BALANCE, config.getInitBalance());
        assertEquals(VALID_RULES_FILENAME, config.getRulesFilename());
        assertEquals(VALID_CARDS_FILENAME, config.getCardsFilename());
        assertEquals(VALID_COLORS.size(), config.getPlayerColors().size());
    }

    @Test
    void builderCannotBeUsedTwice() {
        builder.build();
        final IllegalStateException exception = assertThrows(IllegalStateException.class, builder::build);
        testExceptionFormat(exception);
    }

    @Test
    void configurationInconsistentIfTooFewColors() {
        final List<Color> invalidList = List.of(Color.RED);
        final Configuration config = builder.withColors(invalidList).withMax(invalidList.size() + 1).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "Colors list size < maxPlayers");
    }

    @Test
    void configurationInconsistentIfMinGreaterToMax() {
        final Configuration config = builder.withMin(VALID_MAX + 1).withMax(VALID_MAX).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "minPlayers > maxPlayers");
    }

    @Test
    void configurationInconsistentIfMinIsZeroOrNegative() {
        final Configuration config = builder.withMin(0).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "minPlayers <= 0");
    }

    @Test
    void configurationInconsistentIfNumDicesIsZeroOrNegative() {
        final Configuration config = builder.withNumDice(0).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "numDice <= 0");
    }

    @Test
    void configurationInconsistentIfSidesPerDieIsZeroOrNegative() {
        final Configuration config = builder.withSidesPerDie(0).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "sidesPerDie <= 0");
    }

    @Test
    void configurationInconsistentIfFontInvalid() {
        final Configuration config = builder.withFontName("NonExistentFont").build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "Font name must match an available system font.");
    }

    @Test
    void configurationInconsistentIfSmallFontBiggerThanBigFont() {
        final Configuration config = builder.withSmallFont(VALID_BIG_FONT + 1).withBigFont(VALID_BIG_FONT).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "smallFont > bigFont");
    }

    @Test
    void configurationInconsistentIfBalanceIsNegative() {
        final Configuration config = builder.withInitBalance(-VALID_STARTER_BALANCE).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "starterBalance < 0");
    }

    @Test
    void configurationInconsistentIfRulesFileIsNull() {
        final Configuration config = builder.withRulesFilename(null).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "rulesFilename cannot be null");
    }

    @Test
    void configurationInconsistentIfCardsFileIsNull() {
        final Configuration config = builder.withCardsFilename(null).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "cardsFilename cannot be null");
    }

    @Test
    void configureFromFileReturnsValidOnInvalidConfigFile() throws IOException {
        /*
          Parsing an invalid file should return a configuration where
          valid values are kept and defaults are used for errors or missing entries
        */
        final String filename = "configuration/invalid_config.yml";
        assertFileExists(filename);
        assertTrue(Configuration.configureFromFile(filename).isConsistent(),
                    "Expected configuration to be consistent");
    }

    @Test
    void configureFromFileReturnsDefaultOnFileNotFound() {
        // File that not exist should return a default configuration
        final String filename = "not_exist";
        assertFileNotExists(filename);
        assertTrue(Configuration.configureFromFile(filename).isConsistent(),
                    "Expected default configuration to be consistent");
    }

    @Test
    void configureFromFileParsesValidFileCorrectly() {
        // Parsing a valid file should return a consistent configuration
        final String filename = "configuration/valid_config.yml";
        assertFileExists(filename);
        assertTrue(Configuration.configureFromFile(filename).isConsistent(),
                    "Configuration from valid file should be consistent");
    }



    private void testExceptionFormat(final Exception exception) {
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }

    private void assertFileExists(final String filename) {
        assertTrue(ResourceLoader.checkFilename(filename),
                    "File not found " + filename);
    }

    private void assertFileNotExists(final String filename) {
        assertFalse(ResourceLoader.checkFilename(filename),
                    "File should not be found " + filename);
    }

    private void checkFilenames() {
        for (final String string : VALID_FILENAMES) {
            assertFileExists(string);
        } 
    }
}
