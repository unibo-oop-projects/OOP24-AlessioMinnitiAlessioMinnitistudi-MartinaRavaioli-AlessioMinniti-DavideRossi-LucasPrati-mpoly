package it.unibo.monopoly.utils.impl;

import java.awt.Color;
import java.util.List;
import java.util.Objects;

import it.unibo.monopoly.utils.api.UseConfigurationFile;

/**
 * Represents the game's configuration parameters. 
 */
public final class Configuration {

    private final int minPlayer;
    private final int maxPlayer;
    private final int numDice;
    private final int sidesPerDie;
    private final String fontName;
    private final int fontSize;
    private final int initBalance;
    private final String rulesPath;
    private final String cardsPath;
    private final List<Color> playerColors;



    private Configuration(final int minPlayer, final int maxPlayer, final int numDice, final int sidesPerDie,
                            final String fontName, final int fontSize, final int initBalance,
                            final String rulesPath, final String cardsPath,
                            final List<Color> playerColors) {
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.numDice = numDice;
        this.sidesPerDie = sidesPerDie;
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.initBalance = initBalance;
        this.rulesPath = rulesPath;
        this.cardsPath = cardsPath;
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
     * @return the size of the font
     */
    public int getFontSize() {
        return fontSize;
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
     * @return the path of the file which contains all the cards of the game
     */
    public String getCardsPath() {
        return cardsPath;
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
                && fontSize > 0
                && initBalance >= 0
                && FileChecker.checkPath(rulesPath)
                && FileChecker.checkPath(cardsPath);
    }


    /**
     * Set some values of the application according to the file for the configuration.
     * @param configFile the path of the configuration file
     * @return a {@link Configuration} according to {@code configFile} if consistent. Otherwise a default {@link Configuration}
     */
    public static Configuration configureFromFile(final String configFile) {
        final UseConfigurationFile useFileConfig = new UseConfigurationFileImpl();
        final Configuration configuration = useFileConfig.loadConfiguration(configFile);

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
        private static final String FONT_NAME = "SansSerif";
        private static final int FONT_SIZE = 16;
        private static final int INIT_BALANCE = 2000;
        private static final String RULES_PATH = "rules/rules.txt";
        private static final String CARDS_PATH = "cards/cards.json";
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
        private int fontSize = FONT_SIZE;
        private int initBalance = INIT_BALANCE;
        private String rulesPath = RULES_PATH;
        private String cardspath = CARDS_PATH;
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
         * @param fontSize the size of the font
         * @return this builder, for method chaining
         */
        public Builder withFontSize(final int fontSize) {
            this.fontSize = fontSize;
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
         * @param cardspath the path of the file which contains all the cards of the game
         * @return this builder, for method chaining
         */
        public Builder withCardsPath(final String cardspath) {
            this.cardspath = cardspath;
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
                                    fontName, fontSize, initBalance,
                                    rulesPath, cardspath, playerColors);
        }

        /**
         * Check if the provided {@link Configuration} is equals to the default-one.
         * @param config the {@link Configuration} to check
         * @return true if the provided {@link Configuration} is equals to the default-one, false otherwise
         * @throws NullPointerException if {@code config} is {@code null}
         */
        public static boolean isDefault(final Configuration config) {
            Objects.requireNonNull(config, "Configuration must not be null");
            return MIN_PLAYER    ==  config.getMinPlayer()
                && MAX_PLAYER    ==  config.getMaxPlayer()
                && NUM_DICE      ==  config.getNumDice()
                && SIDES_PER_DIE ==  config.getSidesPerDie()
                && FONT_NAME.equals(config.getFontName())
                && FONT_SIZE    ==  config.getFontSize()
                && INIT_BALANCE  ==  config.getInitBalance()
                && RULES_PATH.equals(config.getRulesPath())
                && CARDS_PATH.equals(config.getCardsPath())
                && PLAYER_COLORS.containsAll(config.getPlayerColors());
        }
    }
}
