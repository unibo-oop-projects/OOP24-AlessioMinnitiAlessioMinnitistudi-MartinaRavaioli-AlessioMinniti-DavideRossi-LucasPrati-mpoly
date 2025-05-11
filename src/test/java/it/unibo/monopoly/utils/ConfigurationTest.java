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

    private static final String INVALID_CONFIG = "Invalid configuration should not be consistent: ";
    private static final int VALID_MIN = 2;
    private static final int VALID_MAX = 4;
    private static final int VALID_WIDTH = 500;
    private static final int VALID_HEIGHT = 400;
    private static final int SMALL_FONT = 16;
    private static final int BIG_FONT = 24;
    private static final int VALID_STARTER_BALANCE = 1500;
    private static final String VALID_FONT = "ARIAL"; // should be available on most systems
    private static final String VALID_RULES_FILENAME = "rules.txt";
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
                .withWidth(VALID_WIDTH)
                .withHeight(VALID_HEIGHT)
                .withFontName(VALID_FONT)
                .withSmallFont(SMALL_FONT)
                .withBigFont(BIG_FONT)
                .withStarterBalance(VALID_STARTER_BALANCE)
                .withRulesFilename(VALID_RULES_FILENAME)
                .withColors(VALID_COLORS);
    }

    @Test
    void buildValidConfiguration() {
        final Configuration config = builder.build();
        assertNotNull(config);
        assertTrue(config.isConsistent());
        assertEquals(VALID_MIN, config.getMinPlayer());
        assertEquals(VALID_MAX, config.getMaxPlayer());
        assertEquals(VALID_WIDTH, config.getWindowWidth());
        assertEquals(VALID_HEIGHT, config.getWindowHeight());
        assertEquals(VALID_FONT, config.getFontName());
        assertEquals(SMALL_FONT, config.getSmallFont());
        assertEquals(BIG_FONT, config.getBigFont());
        assertEquals(VALID_RULES_FILENAME, config.getRulesFilenamename());
        assertEquals(VALID_COLORS.size(), config.getPlayerColors().size());
    }

    @Test
    void builderCannotBeUsedTwice() {
        builder.build();
        final IllegalStateException exception = assertThrows(IllegalStateException.class, builder::build);
        assertNotNull(exception.getMessage());
    }

    @Test
    void configurationInconsistentIfTooFewColors() {
        final List<Color> invalidList = List.of(Color.RED);
        final Configuration config = builder.withColors(invalidList).withMax(invalidList.size() + 1).build();
        assertFalse(config.isConsistent(),
                    INVALID_CONFIG + "Colors list size < maxPlayers");
    }

    @Test
    void configurationInconsistentIfFontInvalid() {
        final Configuration config = builder.withFontName("NonExistentFont").build();
        assertFalse(config.isConsistent(),
                    INVALID_CONFIG + "Font name must match an available system font.");
    }

    @Test
    void configurationInconsistentIfSmallFontBiggerThanBigFont() {
        final Configuration config = builder.withSmallFont(BIG_FONT + 1).withBigFont(BIG_FONT).build();
        assertFalse(config.isConsistent(),
                    INVALID_CONFIG + "smallFont > bigFont");
    }

    @Test
    void configurationInconsistentIfMinGreaterOrEqualToMax() {
        final Configuration config = builder.withMin(VALID_MAX + 1).withMax(VALID_MAX).build();
        assertFalse(config.isConsistent(),
                    INVALID_CONFIG + "minPlayers > maxPlayers");
    }

    @Test
    void configurationInconsistentIfMinEqualToMax() {
        final Configuration config = builder.withMin(VALID_MAX).withMax(VALID_MAX).build();
        assertFalse(config.isConsistent(),
                    INVALID_CONFIG + "minPlayers = maxPlayers");
    }

    @Test
    void configurationInconsistentIfHeightGreaterThanWidth() {
        final Configuration config = builder.withHeight(VALID_WIDTH + 1).withWidth(VALID_WIDTH).build();
        assertFalse(config.isConsistent(),
                    INVALID_CONFIG + "height > width");
    }

    @Test
    void configurationInconsistentIfRulesFileIsNull() {
        final Configuration config = builder.withRulesFilename(null).build();
        assertFalse(config.isConsistent(),
                    INVALID_CONFIG + "rulesFilename can not be null");
    }

    @Test
    void defaultStarterBalanceIsCorrect() {
        final Configuration config = builder.withStarterBalance(0).build();
        assertFalse(config.isConsistent(),
                    INVALID_CONFIG + "starterBalance < 0");
    }

    @Test
    void configureFromFileReturnsDefaultOnInvalidConfig() throws IOException {
        // Parsing an invalid file should return a configuration where
        // valid values are kept and defaults are used for errors or missing entries
        final Configuration config = Configuration.configureFromFile("invalid_config.yml");
        assertTrue(config.isConsistent(), "Expected configuration to be consistent");
    }

    @Test
    void configureFromFileThrowsExceptionOnFileNotFound() {
        // File that not exist should return a default configuration
        final Configuration config = Configuration.configureFromFile("not_exist");
        assertTrue(config.isConsistent(), "Expected default configuration to be consistent");
    }

    @Test
    void configureFromFileParsesValidFileCorrectly() {
        // Parsing a valid file should return a consistent configuration
        final Configuration config = Configuration.configureFromFile("valid_config.yml");
        assertTrue(config.isConsistent(), "Configuration from valid file should be consistent");
    }
}
