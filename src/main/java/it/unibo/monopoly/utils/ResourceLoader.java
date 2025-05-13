package it.unibo.monopoly.utils;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;

/**
 * Utility class for loading and parsing resources such as fonts, colors,
 * configuration files, JSON assets, and plain text files from the classpath.
 * 
 * This class is not meant to be instantiated. All methods are static and stateless.
 */
public final class ResourceLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper()
                                                .registerModule(new Jdk8Module());
    private static record RawDeed(String name, String type, int price, int rent) {}

    private ResourceLoader() { /* Prevent instantiation */ }

    /**
     * Checks whether the given font name is available in the local graphics environment.
     * Ignoring case consideration
     * @param fontName the name of the font to check
     * @return {@code true} if the font is available; {@code false} otherwise
     */
    public static boolean isValidFontName(final String fontName) {
        return  fontName != null
                && Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                            .anyMatch(name -> name.equalsIgnoreCase(fontName));
    }


    /**
     * Checks whether a resource with the given path exists in the classpath.
     * This method attempts to open the resource and immediately closes it.
     * 
     * @param path relative path of the resource
     * @return {@code true} if the resource exists and can be opened; {@code false} otherwise
     */
    public static boolean checkPath(final String path) {
        if (path == null) {
            return false;
        }

        try (InputStream ignored  = getRequiredStream(path)) {
            return true;
        } catch (final IOException e) {
            return false;
        }
    }


    /**
     * Parses a string representing a color name and returns the corresponding {@link Color} object.
     * 
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
     * 
     * @param path relative path of the resource file
     * @return an {@link InputStream} for the specified resource
     * @throws IOException if the resource cannot be found or loaded
     * @throws NullPointerException if {@code path} is {@code null}
     */
    public static InputStream getRequiredStream(final String path) throws IOException {
        Objects.requireNonNull(path, "path must not be null");
        final InputStream stream = ClassLoader.getSystemResourceAsStream(path);
        if (stream == null) {
            throw new IOException("Resource not found: " + path);
        }
        return stream;
    }


    /**
     * Loads a resource from the classpath and returns a buffered reader over its contents.
     * 
     * @param path relative path of the resource file
     * @return a {@link BufferedReader} to read the resource
     * @throws IOException if the resource cannot be found
     * @throws NullPointerException if {@code path} is {@code null}
     */
    public static BufferedReader getRequiredReader(final String path) throws IOException {
        return new BufferedReader(new InputStreamReader(getRequiredStream(path), StandardCharsets.UTF_8));
    }


    /**
     * Reads a text resource from the classpath and returns its contents as a single string.
     * 
     * @param path relative path of the resource
     * @return a {@link String} containing the full contents of the file,
     *         or the error message if the resource could not be loaded
     */
    public static String loadTextResource(final String path) {
        try (BufferedReader reader = getRequiredReader(path)) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return e.getMessage();
        }
    }


    /**
     * Loads a list of objects of the specified type from a JSON file in the classpath.
     * <p>
     * The file must contain a JSON array of elements compatible with the provided {@code type}.
     * 
     * @param <T> the type of elements to load
     * @param filename the relative path of the JSON resource file
     * @param type the class of the target type
     * @return a {@link List} of deserialized objects of type {@code T}
     * @throws NullPointerException if {@code filename} or {@code type} is {@code null}
     * @throws UncheckedIOException if the file cannot be read or parsed
     */
    public static <T> List<T> loadJsonList(final String path, final Class<T> type) {
        Objects.requireNonNull(type);
        final List<T> out;
        try (InputStream fileJson = getRequiredStream(path)) {
            final JavaType outType = MAPPER.getTypeFactory()
                    .constructCollectionLikeType(List.class, type);
            out = MAPPER.readValue(fileJson, outType);
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to convert the Json file", e);
        }
        return out;
    }


    /**
     * Loads a set of {@link TitleDeed} objects from a JSON file in the classpath.
     * 
     * @param path relative path relative path of the JSON resource file
     * @return an unmodifiable {@link Set} of {@link TitleDeed} instances
     * @throws UncheckedIOException if the resource cannot be found or parsed
     */
    public static Set<TitleDeed> loadTitleDeedsFromJson(final String path) {
        List<RawDeed> rawDeeds = loadJsonList(path, RawDeed.class);
        return rawDeeds.stream()
            .map(d -> new BaseTitleDeed(
                d.type(),
                d.name(),
                d.price(),
                price -> price / 2,
                d.rent()
            ))
            .collect(Collectors.toSet());
    }


    /**
     * Loads a {@link Configuration} object from a configuration file in the classpath.
     * Skips malformed or unknown entries gracefully.
     * 
     * @param path relative path of the resource
     * @return a {@link Configuration} object, or a default one if loading fails
     */
    @SuppressWarnings("PMD.AssignmentInOperand") // intentional: idiomatic BufferedReader usage
    @SuppressFBWarnings(
            value = "ASSIGNMENT_IN_CONDITION",
            justification = "Intentional use of assignment in the while condition — "
                            + "a standard practice for reading lines from a BufferedReader in a compact way."
    )
    public static Configuration loadConfigurationFile(final String path) {
        try (BufferedReader reader = getRequiredReader(path)) {
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
     * 
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
                    .map(ResourceLoader::parseColor)
                    .collect(Collectors.toList());

                configurationBuilder.withColors(colors);
            }
            default -> { /* Ignore unknown key */ }
        }
    }

    /**
     * Determines whether a configuration line should be skipped (blank or comment).
     * 
     * @param line the line to check
     * @return {@code true} if the line is blank or a comment; {@code false} otherwise
     */
    private static boolean isSkippable(final String line) {
        return line.isBlank() || line.startsWith("#");
    }

    /**
     * Checks whether a parsed configuration line is malformed.
     * 
     * @param parts the result of splitting the line on ":"
     * @return {@code true} if the split result is invalid; {@code false} otherwise
     */
    private static boolean isMalformed(final String[] parts) {
        return parts.length != 2;
    }
}
