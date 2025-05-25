package it.unibo.monopoly.utils;

import java.awt.Color;
import java.util.List;

import it.unibo.monopoly.utils.api.UseFileConfiguration;
import it.unibo.monopoly.utils.impl.FileChecker;
import it.unibo.monopoly.utils.impl.FontUtils;
import it.unibo.monopoly.utils.impl.UseConfigurationFileImpl;

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
    private final String rulesPath;
    private final String titleDeedsPath;
    private final String tilesPath;
    private final List<Color> playerColors;



    private Configuration(final int minPlayer, final int maxPlayer, final int numDice, final int sidesPerDie,
                            final String fontName, final int bigFont, final int smallFont, final int initBalance,
                            final String rulesPath, final String titleDeedsPath, final String tilesPath,
                            final List<Color> playerColors) {
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.numDice = numDice;
        this.sidesPerDie = sidesPerDie;
        this.fontName = fontName;
        this.bigFont = bigFont;
        this.smallFont = smallFont;
        this.initBalance = initBalance;
        this.rulesPath = rulesPath;
        this.titleDeedsPath = titleDeedsPath;
        this.tilesPath = tilesPath;
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
     * @return the path of the file which contains all the rules of the game
     */
    public String getRulesPath() {
        return rulesPath;
    }

    /**
     * @return the path of the file which contains all the title deeds of the game
     */
    public String getTitleDeedsPath() {
        return titleDeedsPath;
    }

    /**
     * @return the path of the file which contains alla the tiles of the game
     */
    public String getTilesPath() {
        return tilesPath;
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
                && FontUtils.isValidFontName(fontName)
                && smallFont < bigFont
                && initBalance >= 0
                && FileChecker.checkPath(rulesPath)
                && FileChecker.checkPath(titleDeedsPath)
                && FileChecker.checkPath(tilesPath);
    }


    /**
     * Set some values of the application according to the file for the configuration.
     * 
     * @param configFile the path of the configuration file
     * @return a {@link Configuration} according to {@code configFile} if consistent. Otherwise a default {@link Configuration}
     */
    public static Configuration configureFromFile(final String configFile) {
        final UseFileConfiguration useFileConfig = new UseConfigurationFileImpl();
        final Configuration configuration = useFileConfig.loadConfigurationFile(configFile);

        if (configuration.isConsistent()) {
            return configuration;
        }
        return new Configuration.Builder().build();
    }



    /**
     * Builder pattern is used here because.
     * <ul>
     *      <li> All parameters have sensible defaults </li>
     *      <li> Overloading constructors would be confusing and verbose </li>
     *      <li> It allows readable, flexible, chainable configuration setup </li>
     * </ul>
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
        private static final String RULES_PATH = "rules/rules.txt";
        private static final String TITLE_DEEDS_PATH = "cards/title_deeds.json";
        private static final String TILES_PATH = "cards/tiles.json";
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
        private String rulesPath = RULES_PATH;
        private String titleDeedsPath = TITLE_DEEDS_PATH;
        private String tilesPath = TILES_PATH;
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
         * @param rulesPath the path of the file which contains all the rules of the game
         * @return this builder, for method chaining
         */
        public Builder withRulesPath(final String rulesPath) {
            this.rulesPath = rulesPath;
            return this;
        }

        /**
         * @param titleDeedsPath the path of the file which contains all the title deeds of the game
         * @return this builder, for method chaining
         */
        public Builder withTitleDeedsPath(final String titleDeedsPath) {
            this.titleDeedsPath = titleDeedsPath;
            return this;
        }

        /**
         * @param tilesPath the path of the file which contains all the tiles of the game
         * @return this builder, for method chaining
         */
        public Builder withTilesPath(final String tilesPath) {
            this.tilesPath = tilesPath;
            return this;
        }

        /**
         * Sets the list of player colors. If {@code playerColors} is {@code null},
         * this method keep defaults values.
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
                                    rulesPath, titleDeedsPath, tilesPath, playerColors);
        }
    }
}
