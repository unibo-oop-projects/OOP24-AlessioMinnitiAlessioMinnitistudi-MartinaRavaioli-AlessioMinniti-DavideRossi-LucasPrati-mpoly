package it.unibo.monopoly.utils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monopoly.utils.impl.Configuration;


class ConfigurationTest {

    private static final String VALID_CONFIG_YML = "debug/configuration/debug_config.yml";
    private static final String MESSAGE_INVALID_CONFIG = "Invalid configuration should not be consistent: ";
    private static final int VALID_MIN = 2;
    private static final int VALID_MAX = 6;
    private static final int VALID_NUM_DICE = 2;
    private static final int VALID_SIDES_PER_DIE = 6;
    private static final String VALID_FONT = "SansSerif";
    private static final int VALID_FONT_SIZE = 18;
    private static final int VALID_STARTER_BALANCE = 1500;
    private static final String VALID_RULES_PATH = "debug/rules/debug_rules.txt";
    private static final String VALID_CARDS_PATH = "debug/cards/debug_cards.json";
    private static final String VALID_DECK_PATH = "debug/cards/DeckCardTest.txt";
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
                .withFontSize(VALID_FONT_SIZE)
                .withInitBalance(VALID_STARTER_BALANCE)
                .withRulesPath(VALID_RULES_PATH)
                .withCardsPath(VALID_CARDS_PATH)
                .withDeckPath(VALID_DECK_PATH)
                .withColors(VALID_COLORS);
    }

    @Test
    void buildValidConfiguration() {
        final Configuration config = builder.build();
        assertNotNull(config);
        assertTrue(config.isConsistent());
        assertEquals(VALID_MIN, config.getMinPlayer());
        assertEquals(VALID_MAX, config.getMaxPlayer());
        assertEquals(VALID_NUM_DICE, config.getNumDice());
        assertEquals(VALID_SIDES_PER_DIE, config.getSidesPerDie());
        assertEquals(VALID_FONT, config.getFontName());
        assertEquals(VALID_FONT_SIZE, config.getFontSize());
        assertEquals(VALID_STARTER_BALANCE, config.getInitBalance());
        assertEquals(VALID_RULES_PATH, config.getRulesPath());
        assertEquals(VALID_CARDS_PATH, config.getCardsPath());
        assertEquals(VALID_DECK_PATH, config.getDeckPath());
        assertEquals(VALID_COLORS.size(), config.getPlayerColors().size());
    }

    @Test
    void builderCannotBeUsedTwice() {
        builder.build();
        final IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            builder::build
        );
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
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
    void configurationInconsistentIfNegativeFontSize() {
        final Configuration config = builder.withFontSize(-VALID_FONT_SIZE).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "smallFont <= 0");
    }

    @Test
    void configurationInconsistentIfBalanceIsNegative() {
        final Configuration config = builder.withInitBalance(-VALID_STARTER_BALANCE).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "starterBalance < 0");
    }

    @Test
    void configurationInconsistentIfRulesFileIsNull() {
        final Configuration config = builder.withRulesPath(null).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "rulesPath cannot be null");
    }

    @Test
    void configurationInconsistentIfCardsFileIsNull() {
        final Configuration config = builder.withCardsPath(null).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "cardsPath cannot be null");
    }

    @Test
    void configurationInconsistentIfDeckFileIsNull() {
        final Configuration config = builder.withDeckPath(null).build();
        assertFalse(config.isConsistent(),
                    MESSAGE_INVALID_CONFIG + "deckPath cannot be null");
    }

    @Test
    void defaultConfigurationShouldBeTheDefaultOne() {
        final Configuration config = new Configuration.Builder().build();
        assertTrue(Configuration.Builder.isDefault(config),
            "The default configuration should be the default-one");
        assertTrue(config.isConsistent(),
            "The default configuration should be consistent");
    }

    @Test
    void integrationConfigureFromFileWorksWithValidFile() {
        // Integration test: verifies that Configuration can be correctly loaded from a file
        final Configuration config = Configuration.configureFromFile(VALID_CONFIG_YML);
        assertFalse(Configuration.Builder.isDefault(config),
                   "The configuration should not be the default-one."
                   + "Maybe the file does not have a consistent configuration or is equals to the default-one");
        assertTrue(config.isConsistent());
    }
}
