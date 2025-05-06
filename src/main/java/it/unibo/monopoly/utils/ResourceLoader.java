package it.unibo.monopoly.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * A small utility class to load resources (JSON, TXT and YML) from the classpath.
 */
public final class ResourceLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Create a small {@code utility Object} for load resources from the classpath.
     */
    public ResourceLoader() { /* Empty */ }

    /**
     * Loads an array of {@link TitleDeed} from a JSON file on the classpath
     * and returns them as a {@link Set}.
     *
     * @param filename the name of the JSON file in {@code src/main/resources} with all the Title Deeds
     * @return an unmodifiable {@link Set} of {@link TitleDeed}, never {@code null}
     * @throws IOException if the resource is missing or cannot be parsed
     */
    public Set<TitleDeed> loadTitleDeed(final String filename) throws IOException {
        try (InputStream is = getRequiredStream(filename)) {
            final var array = MAPPER.readValue(is, TitleDeed[].class);
            return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(array)));
        }
    }

    /**
     * Reads a resource from the classpath and set a {@link Configuration}.
     * <p>
     * @param filename the name of the YML file in {@code src/main/resources} with all the custom data
     * @return a {@link Configuration} with the custom data provided by the file
     */
    public Configuration loadConfigurationFile(final String filename) {
        // find the file
        final InputStream is;
        try {
            is = getRequiredStream(filename);
        } catch (final IOException e) {
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
                    parseConfigurationKey(configurationBuilder, key, value);
                } catch (final IllegalArgumentException e) {
                    // Error during parseColor
                    continue;
                }
            }
        } catch (final IOException  e) {    // Error during reading the file
            // return a consistent default configuration
            return new Configuration.Builder().build();
        }
        return configurationBuilder.build();
    }


    /**
     * Helper that opens a classpath resource as an InputStream.
     * <p>
     * @param filename the name of the file in {@code src/main/resources} we want to get
     * @throws IOException if the file is missing
     * @return an {@link InputStream} to the classpath resource
     */
    private InputStream getRequiredStream(final String filename) throws IOException {
        final InputStream stream = getClass().getResourceAsStream(filename);
        if (stream == null) {
            throw new IOException("Resource not found: " + filename);
        }
        return stream;
    }


    /**
     * Parses a provided {@code key-value} for set configutation's parameters.
     * <p>
     * @param configurationBuilder the {@link Configuration.Builder} we want to upload the data
     * @param key the name of the element to parse
     * @param value tha value of the element to parse
     * @throws IllegalArgumentException if the underlying implementation does not parse value successfully
     */
    private void parseConfigurationKey(final Configuration.Builder configurationBuilder,
                                              final String key,
                                              final String value) {
        switch (key) {
            case "MIN_PLAYERS" -> configurationBuilder.withMin(Integer.parseInt(value));
            case "MAX_PLAYERS" -> configurationBuilder.withMax(Integer.parseInt(value));
            case "WINDOW_WIDTH" -> configurationBuilder.withWidth(Integer.parseInt(value));
            case "WINDOW_HEIGHT" -> configurationBuilder.withHeight(Integer.parseInt(value));
            case "FONT_NAME" -> configurationBuilder.withFontName(value);
            case "BIG_FONT" -> configurationBuilder.withBigFont(Integer.parseInt(value));
            case "SMALL_FONT" -> configurationBuilder.withSmallFont(Integer.parseInt(value));
            case "INIT_BALANCE" -> configurationBuilder.withInitBalance(Integer.parseInt(value));
            case "RULES_FILE" -> configurationBuilder.withRulesFilename(value);
            case "COLORS" -> {
                final List<Color> colors = Arrays.stream(value.split(","))
                    .map(String::trim)
                    .map(ResourceLoader::parseColor)
                    .collect(Collectors.toList());

                configurationBuilder.withColors(colors);
            }
            default -> { /* Ignore unknown key */ }
        }
    }

    /**
     * Parses a string representing a color name and returns the corresponding {@link Color} object.
     * <p>
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
}
