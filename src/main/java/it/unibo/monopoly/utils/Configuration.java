package it.unibo.monopoly.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Represents the game's configuration parameters,
 * including player limits, window size, and color assignments.
 */
public final class Configuration {

    private final int maxPlayer; 
    private final int minPlayer;
    private final String fontName;
    private final int smallFont;
    private final int bigFont;
    private final int windowHeight;
    private final int windowWidth;
    private final int starterBalance;
    private final String rulesFilename;
    private final List<Color> playerColors;


    private Configuration(final int maxPlayer, final int minPlayer, final String fontName, final int smallFont,
                          final int bigFont, final int windowHeight, final int windowWidth, final int starterBalance,
                          final String rulesFilename, final List<Color> playerColors) {
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
        this.fontName = fontName;
        this.smallFont = smallFont;
        this.bigFont = bigFont;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.starterBalance = starterBalance;
        this.rulesFilename = rulesFilename;
        this.playerColors = playerColors;
    }

    /**
     * @return the maximum number of players
     */
    public int getMaxPlayer() {
        return maxPlayer;
    }

    /**
     * @return the minimum number of players
     */
    public int getMinPlayer() {
        return minPlayer;
    }

    /**
     * @return the name of the font to use
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * @return the minimum size of the font
     */
    public int getSmallFont() {
        return smallFont;
    }

    /**
     * @return the maximum size of the font
     */
    public int getBigFont() {
        return bigFont;
    }

    /**
     * @return the height of the window
     */
    public int getWindowHeight() {
        return windowHeight;
    }

    /**
     * @return the width of the window
     */
    public int getWindowWidth() {
        return windowWidth;
    }

    /**
     * @return the initial amount of each bank account
     */
    public int getStarterBalance() {
        return starterBalance;
    }

    /**
     * @return the name of the file which contains all the rules of the game
     */
    public String getRulesFilenamename() {
        return rulesFilename;
    }

    /**
     * @return the list of colors assigned to players
     */
    public List<Color> getPlayerColors() {
        return List.copyOf(playerColors);
    }

    /**
     * @return true if the configuration is consistent
     */
    public boolean isConsistent() {
        return playerColors.size() >= maxPlayer
                && minPlayer < maxPlayer
                && windowHeight <= windowWidth
                && smallFont < bigFont
                && Objects.nonNull(rulesFilename)
                && isValidFontName(fontName)
                && starterBalance > 0;
    }

    /**
     * Parses a string representing a color name and returns the corresponding {@link Color} object.
     *
     * @param name the name of the color (case-insensitive)
     * @return the {@link Color} object corresponding to the given name
     * @throws IllegalArgumentException if the given {@param name} does not match any supported color
    */
    private static Color parseColor(final String name) {
        return switch (name.toUpperCase(Locale.ENGLISH)) {
            case "BLACK" -> Color.BLACK;
            case "BLUE" -> Color.BLUE;
            case "CYAN" -> Color.CYAN;
            case "DARK_GRAY" -> Color.DARK_GRAY;
            case "GRAY" -> Color.GRAY;
            case "GREEN" -> Color.GREEN;
            case "LIGHT_GRAY" -> Color.LIGHT_GRAY;
            case "MAGENTA" -> Color.MAGENTA;
            case "ORANGE" -> Color.ORANGE;
            case "PINK" -> Color.PINK;
            case "RED" -> Color.RED;
            case "WHITE" -> Color.WHITE;
            case "YELLOW" -> Color.YELLOW;
            default -> throw new IllegalArgumentException("Unknown color: " + name);
        };
    }

    private static boolean isValidFontName(final String fontName) {
        return  Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                               .anyMatch(name -> name.equalsIgnoreCase(fontName)) && Objects.nonNull(fontName);
    }


    /**
     * @param configFile the name of the configuration file
     * @return a configuration according to { @param configFile } if consistent, otherwise a default configuration
     */
    public static Configuration configureFromFile(final String configFile) {
        // try to find the file
        final InputStream is = Configuration.class.getClassLoader().getResourceAsStream(configFile);

        // if not found, return a consistent default configuration
        if (Objects.isNull(is)) {
            return new Configuration.Builder().build();
        }

        // if found, read it
        final Configuration.Builder configurationBuilder = new Configuration.Builder();
        try (var contents = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            for (String configLine = contents.readLine(); configLine != null; configLine = contents.readLine()) {
                if (configLine.isBlank() || configLine.startsWith("#")) {
                    continue;   // Skip empty lines and comments
                }

                final String[] lineElements = configLine.split(":", 2);
                if (lineElements.length != 2) {
                    continue;   // Skip invalid lines
                }

                final String key = lineElements[0].trim().toUpperCase(Locale.ENGLISH);
                final String value = lineElements[1].trim();
                try {
                    switch (key) {
                        case "MIN_PLAYERS" -> configurationBuilder.withMin(Integer.parseInt(value));
                        case "MAX_PLAYERS" -> configurationBuilder.withMax(Integer.parseInt(value));
                        case "WINDOW_WIDTH" -> configurationBuilder.withWidth(Integer.parseInt(value));
                        case "WINDOW_HEIGHT" -> configurationBuilder.withHeight(Integer.parseInt(value));
                        case "FONT_NAME" -> configurationBuilder.withFontName(value);
                        case "BIG_FONT" -> configurationBuilder.withBigFont(Integer.parseInt(value));
                        case "SMALL_FONT" -> configurationBuilder.withSmallFont(Integer.parseInt(value));
                        case "STARTER_BALANCE" -> configurationBuilder.withStarterBalance(Integer.parseInt(value));
                        case "RULES_FILE" -> configurationBuilder.withRulesFilename(value);
                        case "COLORS" -> {
                            final List<Color> colors = Arrays.stream(value.split(","))
                                .map(String::trim)
                                .map(Configuration::parseColor)
                                .collect(Collectors.toList());

                            configurationBuilder.withColors(colors);
                        }
                        default -> { /* Ignore unknown key */ }
                    }
                } catch (final IllegalArgumentException e) {
                    // Error during parseColor
                    continue;
                }
            }

        } catch (final IOException  e) {    // Error during reading the file
            // return a consistent default configuration
            return new Configuration.Builder().build();
        }

        // If the configuration is consistent, return it. Otherwise return a consistent default configuration
        final Configuration configuration = configurationBuilder.build();
        if (configuration.isConsistent()) {
            return configuration;

        } else {
            return new Configuration.Builder().build();
        }
    }



    /**
     * Pattern builder: used here because:
     * 
     * - all the parameters of the {@link Configuration} class have a default value, which
     * means that we would like to have all the possible combinations of
     * constructors (one with three parameters, three with two parameters, three
     * with a single parameter), which are way too many and confusing to use
     * 
     * - moreover, it would be impossible to provide all of them, because they are
     * all of the same type, and only a single constructor can exist with a given
     * list of parameter types.
     * 
     * - the Configuration class has three parameters of the same type, and it is
     * unclear to understand, in a call to its contructor, which is which. By using
     * the builder, we emulate the so-called "named arguments".
     * 
     */
    public static class Builder {

        private static final int MAX_PLAYER = 4; 
        private static final int MIN_PLAYER = 2;
        private static final String FONT_NAME = "ARIAL";
        private static final int BIG_FONT = 24;
        private static final int SMALL_FONT = 16;
        private static final int WINDOW_HEIGHT = 400;
        private static final int WINDOW_WIDTH = 500;
        private static final int STARTER_BALANCE = 2000;
        private static final String RULES_FILENAME = "rules.txt";
        private static final List<Color> PLAYER_COLORS = List.of(
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

        // Builder's default fields
        private int maxPlayer = MAX_PLAYER;
        private int minPlayer = MIN_PLAYER;
        private String fontName = FONT_NAME;
        private int bigFont = BIG_FONT;
        private int smallFont = SMALL_FONT;
        private int windowHeight = WINDOW_HEIGHT;
        private int windowWidth = WINDOW_WIDTH;
        private int starterBalance = STARTER_BALANCE;
        private String rulesFilename = RULES_FILENAME;
        private List<Color> playerColors = List.copyOf(PLAYER_COLORS);
        private boolean consumed;

        /**
         * @param minPlayer the minimum number of players
         * @return this builder, for method chaining
         */
        public Builder withMin(final int minPlayer) {
            this.minPlayer = minPlayer;
            return this;
        }

        /**
         * @param maxPlayer the maximum number of players
         * @return this builder, for method chaining
         */
        public Builder withMax(final int maxPlayer) {
            this.maxPlayer = maxPlayer;
            return this;
        }

        /**
         * @param fontName the name of the font to use
         * @return this builder, for method chaining
         */
        public Builder withFontName(final String fontName) {
            this.fontName = fontName;
            return this;
        }

        /**
         * @param smallFont the minimum size of the font
         * @return this builder, for method chaining
         */
        public Builder withSmallFont(final int smallFont) {
            this.smallFont = smallFont;
            return this;
        }

        /**
         * @param bigFont the maximum size of the font
         * @return this builder, for method chaining
         */
        public Builder withBigFont(final int bigFont) {
            this.bigFont = bigFont;
            return this;
        }

        /**
         * @param windowHeight the height of the window
         * @return this builder, for method chaining
         */
        public Builder withHeight(final int windowHeight) {
            this.windowHeight = windowHeight;
            return this;
        }

        /**
         * @param windowWidth the width of the window
         * @return this builder, for method chaining
         */
        public Builder withWidth(final int windowWidth) {
            this.windowWidth = windowWidth;
            return this;
        }

        /**
         * @param starterBalance the initial balance of each bank account
         * @return this builder, for method chaining
         */
        public Builder withStarterBalance(final int starterBalance) {
            this.starterBalance = starterBalance;
            return this;
        }

        /**
         * 
         * @param rulesFilename the name of the file which contains all the rules of the game
         * @return this builder, for method chaining
         */
        public Builder withRulesFilename(final String rulesFilename) {
            this.rulesFilename = rulesFilename;
            return this;
        }

        /**
         * @param playerColors the colors of the players
         * @return this builder, for method chaining
         */
        public Builder withColors(final List<Color> playerColors) {
            this.playerColors = List.copyOf(playerColors);
            return this;
        }

        /**
         * @return a configuration
         */
        public final Configuration build() {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;
            return new Configuration(maxPlayer, minPlayer, fontName, smallFont, bigFont, 
                                    windowHeight, windowWidth, starterBalance, rulesFilename, playerColors);
        }
    }
}
