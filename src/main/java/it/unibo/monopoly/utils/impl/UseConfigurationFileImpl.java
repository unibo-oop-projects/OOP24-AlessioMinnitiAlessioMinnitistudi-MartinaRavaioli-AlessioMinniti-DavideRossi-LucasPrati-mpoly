package it.unibo.monopoly.utils.impl;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import it.unibo.monopoly.utils.Configuration;
import it.unibo.monopoly.utils.api.UseFileConfiguration;

/**
 * Implementation of {@link UseFileConfiguration} for parsing
 * a structured configuration file into a {@link Configuration} object.
 * <p>
 * This class supports comment lines, blank lines, and validates known configuration keys.
 * Malformed or unknown entries are skipped silently.
 */
public class UseConfigurationFileImpl extends AbstractUseFileImpl implements UseFileConfiguration{
    
    /**
     * Loads a {@link Configuration} object from a configuration file in the classpath.
     * Skips malformed or unknown entries gracefully.
     * 
     * @param path relative path of the resource
     * @return a {@link Configuration} object, or a default one if loading fails
     */
    public Configuration loadConfigurationFile(final String path) {
        try (BufferedReader reader = getRequiredReader(path)) {
            final Configuration.Builder builder = new Configuration.Builder();

            reader.lines()
                .filter(line -> !isSkippable(line))
                .map(line -> line.split(":", 2))
                .filter(parts -> !isMalformed(parts))
                .forEach(parts -> {
                    final String key = parts[0].trim().toUpperCase(Locale.ENGLISH);
                    final String value = parts[1].trim();
                    try {
                        parseConfigurationKey(builder, key, value);
                    } catch (final IllegalArgumentException e) {
                        // Skip invalid line silently
                        Objects.requireNonNull(e); // Suppress empty-catch-blocks warning
                    }
                });

            return builder.build();

        } catch (final IOException e) {
            return new Configuration.Builder().build();
        }
    }


    /**
     * Parses a string representing a color name and returns the corresponding {@link Color} object.
     * @param name the name of the color (case-insensitive)
     * @return a {@link Color} object matching the given name
     * @throws IllegalArgumentException if the color name is unknown
     * @throws NullPointerException if the name of the color is {@code null}
     */
    private Color parseColor(final String name) {
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


    /**
     * Parses a key-value pair from the configuration file and applies it to the {@link Configuration.Builder}.
     * @param configurationBuilder the {@link Configuration.Builder} to apply the parsed setting to
     * @param key the configuration key
     * @param value the associated value to parse
     * @throws IllegalArgumentException if the key is recognized but the value is invalid
     */
    private void parseConfigurationKey(final Configuration.Builder configurationBuilder,
                                              final String key,
                                              final String value) {
        switch (key) {
            case "MIN_PLAYERS" -> configurationBuilder.withMin(Integer.parseInt(value));
            case "MAX_PLAYERS" -> configurationBuilder.withMax(Integer.parseInt(value));
            case "NUM_DICE" -> configurationBuilder.withNumDice(Integer.parseInt(value));
            case "SIDES_PER_DIE" -> configurationBuilder.withSidesPerDie(Integer.parseInt(value));
            case "FONT_NAME" -> configurationBuilder.withFontName(value);
            case "BIG_FONT" -> configurationBuilder.withBigFont(Integer.parseInt(value));
            case "SMALL_FONT" -> configurationBuilder.withSmallFont(Integer.parseInt(value));
            case "INIT_BALANCE" -> configurationBuilder.withInitBalance(Integer.parseInt(value));
            case "RULES_FILE" -> configurationBuilder.withRulesPath(value);
            case "TITLE_DEEDS_FILE" -> configurationBuilder.withTitleDeedsPath(value);
            case "TILES_FILE" -> configurationBuilder.withTilesPath(value);
            case "COLORS" -> {
                final List<Color> colors = Arrays.stream(value.split(","))
                    .map(String::trim)
                    .map(this::parseColor)
                    .collect(Collectors.toList());

                configurationBuilder.withColors(colors);
            }
            default -> { /* Ignore unknown key */ }
        }
    }


    /**
     * Determines whether a configuration line should be skipped (blank or comment).
     * @param line the line to check
     * @return {@code true} if the line is blank or a comment; {@code false} otherwise
     */
    private boolean isSkippable(final String line) {
        return line.isBlank() || line.startsWith("#");
    }


    /**
     * Checks whether a parsed configuration line is malformed.
     * @param parts the result of splitting the line on ":"
     * @return {@code true} if the split result is invalid; {@code false} otherwise
     */
    private boolean isMalformed(final String[] parts) {
        return parts.length != 2;
    }
}
