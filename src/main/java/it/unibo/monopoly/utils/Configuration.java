package it.unibo.monopoly.utils;

import java.util.List;
import java.awt.Color;

/**
 * Represents the game's configuration parameters,
 * including player limits, window size, and color assignments.
 */
public final class Configuration {

    private final int maxPlayer; 
    private final int minPlayer;
    private final int windowHeight;
    private final int windowWidth;
    private final List<Color> playerColors;


    private Configuration(int maxPlayer, int minPlayer, int windowHeight, int windowWidth, List<Color> playerColors) {
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
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
     * @return the list of colors assigned to players
     */
    public List<Color> getPlayerColors() {
        return playerColors;
    }

    /**
     * @return true if the configuration is consistent
     */
    public boolean isConsistent() {
        return playerColors.size() >= maxPlayer && minPlayer < maxPlayer && windowHeight <= windowWidth;
    }

    /**
     * Pattern builder: used here because:
     * 
     * - all the parameters of the Configuration class have a default value, which
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

        private static final int MAX_PLAYER = 6; 
        private static final int MIN_PLAYER = 2;
        private static final int WINDOW_HEIGHT = 400;
        private static final int WINDOW_WIDTH = 500;
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
        private int windowHeight = WINDOW_HEIGHT;
        private int windowWidth = WINDOW_WIDTH;
        private List<Color> playerColors = List.copyOf(PLAYER_COLORS);
        private boolean consumed = false;

        /**
         * @param min the minimum number of players
         * @return this builder, for method chaining
         */
        public Builder setMin(final int minPlayer) {
            this.minPlayer = minPlayer;
            return this;
        }

        /**
         * @param max the maximum number of players
         * @return this builder, for method chaining
         */
        public Builder setMax(final int maxPlayer) {
            this.maxPlayer = maxPlayer;
            return this;
        }

        /**
         * @param windowHeight the height of the window
         * @return this builder, for method chaining
         */
        public Builder setHeight(final int windowHeight) {
            this.windowHeight = windowHeight;
            return this;
        }

        /**
         * @param windowWidth the width of the window
         * @return this builder, for method chaining
         */
        public Builder setWidth(final int windowWidth) {
            this.windowWidth = windowWidth;
            return this;
        }

        /**
         * @param playerColors the colors of the players
         * @return this builder, for method chaining
         */
        public Builder setColors(final List<Color> playerColors) {
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
            return new Configuration(maxPlayer, minPlayer, windowHeight, windowWidth, playerColors);
        }
    }
}
