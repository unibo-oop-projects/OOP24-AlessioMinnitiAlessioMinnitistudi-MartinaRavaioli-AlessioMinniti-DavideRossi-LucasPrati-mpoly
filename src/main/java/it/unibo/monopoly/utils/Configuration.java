package it.unibo.monopoly.utils;

import java.awt.Color;
import java.util.List;

/**
 * Represents the game's configuration parameters. 
 */
public final class Configuration {

    private final int minPlayer;
    private final int maxPlayer;
    private final int numDice;
    private final int sidesPerDie;
    private final String fontName;
    private final int bigFont;
    private final int smallFont;
    private final int initBalance;
    private final String rulesFilename;
    private final String titleDeedsFilename;
    private final List<Color> playerColors;



    private Configuration(final int minPlayer, final int maxPlayer, final int numDice, final int sidesPerDie,
                            final String fontName, final int bigFont, final int smallFont, final int initBalance,
                            final String rulesFilename, final String titleDeedsFilename, final List<Color> playerColors) {
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.numDice = numDice;
        this.sidesPerDie = sidesPerDie;
        this.fontName = fontName;
        this.bigFont = bigFont;
        this.smallFont = smallFont;
        this.initBalance = initBalance;
        this.rulesFilename = rulesFilename;
        this.titleDeedsFilename = titleDeedsFilename;
        this.playerColors = playerColors;
    }



    /**
     * @return the minimum number of players
     */
    public int getMinPlayer() {
        return minPlayer;
    }

    /**
     * @return the maximum number of players
     */
    public int getMaxPlayer() {
        return maxPlayer;
    }

    /**
     * @return the number of dice
     */
    public int getNumDice() {
        return numDice;
    }

    /**
     * @return the number of sides for each die
     */
    public int getSidesPerDie() {
        return sidesPerDie;
    }

    /**
     * @return the name of the font to use
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * @return the maximum size of the font
     */
    public int getBigFont() {
        return bigFont;
    }

    /**
     * @return the minimum size of the font
     */
    public int getSmallFont() {
        return smallFont;
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
     * @return the name of the file which contains all the title deeds of the game
     */
    public String getTitleDeedsFilename() {
        return titleDeedsFilename;
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
                && minPlayer > 0
                && minPlayer <= maxPlayer
                && numDice > 0
                && sidesPerDie > 0
                && ResourceLoader.isValidFontName(fontName)
                && smallFont < bigFont
                && initBalance >= 0
                && ResourceLoader.checkFilename(rulesFilename)
                && ResourceLoader.checkFilename(titleDeedsFilename);
    }


    /**
     * Set some values of the application according to the file for the configuration ({@code filename}).
     * 
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
     * 
     * - All parameters have sensible defaults
     * - Overloading constructors would be confusing and verbose
     * - It allows readable, flexible, chainable configuration setup
     * 
     */
    public static class Builder {

        private static final int MIN_PLAYER = 2;
        private static final int MAX_PLAYER = 4; 
        private static final int NUM_DICE = 2;
        private static final int SIDES_PER_DIE = 6;
        private static final String FONT_NAME = "ARIAL";
        private static final int BIG_FONT = 24;
        private static final int SMALL_FONT = 16;
        private static final int INIT_BALANCE = 2000;
        private static final String RULES_FILENAME = "rules/rules.txt";
        private static final String TITLE_DEEDS_FILENAME = "cards/title_deeds.json";
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
        private int minPlayer = MIN_PLAYER;
        private int maxPlayer = MAX_PLAYER;
        private int numDice = NUM_DICE;
        private int sidesPerDie = SIDES_PER_DIE;
        private String fontName = FONT_NAME;
        private int bigFont = BIG_FONT;
        private int smallFont = SMALL_FONT;
        private int initBalance = INIT_BALANCE;
        private String rulesFilename = RULES_FILENAME;
        private String titleDeedsFilename = TITLE_DEEDS_FILENAME;
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
         * @param numDice the number of dice
         * @return this builder, for method chaining
         */
        public Builder withNumDice(final int numDice) {
            this.numDice = numDice;
            return this;
        }

        /**
         * @param sidesPerDie the number of sides for each die
         * @return this builder, for method chaining
         */
        public Builder withSidesPerDie(final int sidesPerDie) {
            this.sidesPerDie = sidesPerDie;
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
         * @param bigFont the maximum size of the font
         * @return this builder, for method chaining
         */
        public Builder withBigFont(final int bigFont) {
            this.bigFont = bigFont;
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
         * @param titleDeedsFilename the name of the file which contains all the cards of the game
         * @return this builder, for method chaining
         */
        public Builder withTitleDeedsFilename(final String titleDeedsFilename) {
            this.titleDeedsFilename = titleDeedsFilename;
            return this;
        }

        /**
         * Sets the list of player colors. If {@code playerColors} is {@code null},
         * this method does nothing and retains the default configuration.
         * 
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
         * Builds a new {@link Configuration} instance using the parameters specified so far.
         * <p>
         * This builder is single-use: after this method is called once, further calls will throw
         * an {@link IllegalStateException}.
         * 
         * @return the constructed {@link Configuration}
         * @throws IllegalStateException if this method is called more than once
         */
        public final Configuration build() {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;
            return new Configuration(minPlayer, maxPlayer, numDice, sidesPerDie,
                                    fontName, bigFont, smallFont, initBalance,
                                    rulesFilename, titleDeedsFilename, playerColors);
        }
    }
}
