package it.unibo.monopoly.utils;

import java.awt.Color;
import java.util.List;

/**
 * Represents the game's configuration parameters. 
 */
public final class Configuration {

    private final int maxPlayer; 
    private final int minPlayer;
    private final String fontName;
    private final int smallFont;
    private final int bigFont;
    private final int initBalance;
    private final String rulesFilename;
    private final List<Color> playerColors;


    private Configuration(final int maxPlayer, final int minPlayer, final String fontName, final int smallFont,
                          final int bigFont, final int initBalance, final String rulesFilename,
                          final List<Color> playerColors) {
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
        this.fontName = fontName;
        this.smallFont = smallFont;
        this.bigFont = bigFont;
        this.initBalance = initBalance;
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
     * @return the initial amount of each bank account
     */
    public int getInitBalance() {
        return initBalance;
    }

    /**
     * @return the name of the file which contains all the rules of the game
     */
    public String getRulesFilename() {
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
                && smallFont < bigFont
                && ResourceLoader.checkFilename(rulesFilename)
                && ResourceLoader.isValidFontName(fontName)
                && initBalance > 0;
    }


    /**
     * Set some values of the application according to the file for the configuration ({@code filename}).
     * <p>
     * @param configFile the name of the configuration file
     * @return a {@link Configuration} according to {@code configFile} if consistent. Otherwise a default {@link Configuration}
     */
    public static Configuration configureFromFile(final String configFile) {
        final Configuration configuration = ResourceLoader.loadConfigurationFile(configFile);

        if (configuration.isConsistent()) {
            return configuration;
        }
        return new Configuration.Builder().build();
    }



    /**
     * Builder pattern is used here because.
     * <p>
     * - All parameters have sensible defaults
     * - Overloading constructors would be confusing and verbose
     * - It allows readable, flexible, chainable configuration setup
     * 
     */
    public static class Builder {

        private static final int MAX_PLAYER = 4; 
        private static final int MIN_PLAYER = 2;
        private static final String FONT_NAME = "ARIAL";
        private static final int BIG_FONT = 24;
        private static final int SMALL_FONT = 16;
        private static final int INIT_BALANCE = 2000;
        private static final String RULES_FILENAME = "rules/rules.txt";
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
        private int initBalance = INIT_BALANCE;
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
         * @param fontName the name of the font to use;
         * if null, consistency check will fail and default configuration will be used
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
         * @param initBalance the initial balance of each bank account
         * @return this builder, for method chaining
         */
        public Builder withInitBalance(final int initBalance) {
            this.initBalance = initBalance;
            return this;
        }

        /**
         * @param rulesFilename the name of the file which contains all the rules of the game
         * @return this builder, for method chaining
         */
        public Builder withRulesFilename(final String rulesFilename) {
            this.rulesFilename = rulesFilename;
            return this;
        }

        /**
         * Sets the list of player colors. If {@code playerColors} is {@code null},
         * this method does nothing and retains the default configuration.
         * <p>
         * @param playerColors the list of player colors, or {@code null} to keep defaults
         * @return this builder, for method chaining
         */
        public Builder withColors(final List<Color> playerColors) {
            if (playerColors != null) {
                this.playerColors = List.copyOf(playerColors);
            }
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
                                     initBalance, rulesFilename, playerColors);
        }
    }
}
