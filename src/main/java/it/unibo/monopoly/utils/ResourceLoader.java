package it.unibo.monopoly.utils;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
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
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

/**
 * Utility class for loading and parsing resources such as fonts, colors,
 * configuration files, JSON assets, and plain text files from the classpath.
 * <p>
 * This class is not meant to be instantiated. All methods are static and stateless.
 */
public final class ResourceLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ResourceLoader() { /* Prevent instantiation */ }

    /**
     * Checks whether the given font name is available in the local graphics environment.
     * <p>
     * @param fontName the name of the font to check
     * @return {@code true} if the font is available; {@code false} otherwise
     */
    public static boolean isValidFontName(final String fontName) {
        return  fontName != null
                && Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                            .anyMatch(name -> name.equalsIgnoreCase(fontName));
    }


    /**
     * Checks whether a resource with the given filename exists in the classpath.
     * This method attempts to open the resource and immediately closes it.
     * <p>
     * @param filename the name (and optional path) of the resource
     * @return {@code true} if the resource exists and can be opened; {@code false} otherwise
     */
    public static boolean checkFilename(final String filename) {
        if (filename == null) {
            return false;
        }

        try (InputStream ignored  = getRequiredStream(filename)) {
            return true;
        } catch (final IOException e) {
            return false;
        }
    }


    /**
     * Parses a string representing a color name and returns the corresponding {@link Color} object.
     * <p>
     * @param name the name of the color (case-insensitive)
     * @return a {@link Color} object matching the given name
     * @throws IllegalArgumentException if the color name is unknown or {@code null}
     */
    public static Color parseColor(final String name) {
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
     * Loads a resource from the classpath as an {@link InputStream}.
     * <p>
     * @param filename the name (and optional path) of the resource file
     * @return an {@link InputStream} for the specified resource
     * @throws IOException if the resource cannot be found or loaded
     */
    public static InputStream getRequiredStream(final String filename) throws IOException {
        Objects.requireNonNull(filename, "filename must not be null");
        final ClassLoader cl = Thread.currentThread().getContextClassLoader();
        final InputStream stream = cl.getResourceAsStream(filename);
        if (stream == null) {
            throw new IOException("Resource not found: " + filename);
        }
        return stream;
    }


    /**
     * Loads a resource from the classpath and returns a buffered reader over its contents.
     * <p>
     * @param filename the name (and optional path) of the resource file
     * @return a {@link BufferedReader} to read the resource
     * @throws IOException if the resource cannot be found
     */
    public static BufferedReader getRequiredReader(final String filename) throws IOException {
        return new BufferedReader(new InputStreamReader(getRequiredStream(filename), StandardCharsets.UTF_8));
    }


    /**
     * Reads a text resource from the classpath and returns its contents as a single string.
     * <p>
     * @param filename the name (and optional relative path) of the resource
     * @return a {@link String} containing the full contents of the file,
     *         or the error message if the resource could not be loaded
     */
    public static String loadTextResource(final String filename) {
        try (BufferedReader reader = getRequiredReader(filename)) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return e.getMessage();
        }
    }


    /**
     * Loads a set of {@link TitleDeed} objects from a JSON file in the classpath.
     * <p>
     * @param filename the name (and optional relative path) of the JSON resource file
     * @return an unmodifiable {@link Set} of {@link TitleDeed} instances
     * @throws IOException if the resource cannot be found or parsed
     */
    public static Set<TitleDeed> loadTitleDeed(final String filename) throws IOException {
        try (InputStream is = getRequiredStream(filename)) {
            final var array = MAPPER.readValue(is, TitleDeed[].class);
            return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(array)));
        }
    }


    /**
     * Loads a {@link Configuration} object from a configuration file in the classpath.
     * Skips malformed or unknown entries gracefully.
     * <p>
     * @param filename the name (and optional relative path) of the resource
     * @return a {@link Configuration} object, or a default one if loading fails
     */
    @SuppressWarnings("PMD.AssignmentInOperand") // intentional: idiomatic BufferedReader usage
    @SuppressFBWarnings(
            value = "ASSIGNMENT_IN_CONDITION",
            justification = "Intentional use of assignment in the while condition — "
                            + "a standard practice for reading lines from a BufferedReader in a compact way."
    )
    public static Configuration loadConfigurationFile(final String filename) {
        try (BufferedReader reader = getRequiredReader(filename)) {
            final Configuration.Builder builder = new Configuration.Builder();

            String line;
            while ((line = reader.readLine()) != null) {
                if (isSkippable(line)) {
                    continue;
                }

                final String[] parts  = line.split(":", 2);
                if (isMalformed(parts)) {
                    continue;
                }

                final String key = parts[0].trim().toUpperCase(Locale.ENGLISH);
                final String value = parts[1].trim();

                try {
                    parseConfigurationKey(builder, key, value);
                } catch (final IllegalArgumentException e) {
                    // Skipping unsupported or invalid configuration entry
                    continue;
                }
            }
            return builder.build();

        } catch (final IOException e) {
            return new Configuration.Builder().build();
        }
    }




    /**
     * Parses a key-value pair from the configuration file and applies it to the {@link Configuration.Builder}.
     * <p>
     * @param configurationBuilder the {@link Configuration.Builder} to apply the parsed setting to
     * @param key the configuration key
     * @param value the associated value to parse
     * @throws IllegalArgumentException if the key is recognized but the value is invalid
     */
    private static void parseConfigurationKey(final Configuration.Builder configurationBuilder,
                                              final String key,
                                              final String value) {
        switch (key) {
            case "MIN_PLAYERS" -> configurationBuilder.withMin(Integer.parseInt(value));
            case "MAX_PLAYERS" -> configurationBuilder.withMax(Integer.parseInt(value));
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
     * Determines whether a configuration line should be skipped (blank or comment).
     * <p>
     * @param line the line to check
     * @return {@code true} if the line is blank or a comment; {@code false} otherwise
     */
    private static boolean isSkippable(final String line) {
        return line.isBlank() || line.startsWith("#");
    }

    /**
     * Checks whether a parsed configuration line is malformed.
     * <p>
     * @param parts the result of splitting the line on ":"
     * @return {@code true} if the split result is invalid; {@code false} otherwise
     */
    private static boolean isMalformed(final String[] parts) {
        return parts.length != 2;
    }
}
