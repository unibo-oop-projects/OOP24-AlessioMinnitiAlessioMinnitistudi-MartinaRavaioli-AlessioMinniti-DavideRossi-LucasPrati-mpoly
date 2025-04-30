package it.unibo.monopoly.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibo.monopoly.model.transactions.api.TitleDeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A small utility class to load resources (JSON, text) from the classpath.
 * All methods are static, this class cannot be instantiated.
 */
public final class ResourceLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ResourceLoader() { /* non istanziabile */ }

    /**
     * Loads an array of {@link TitleDeed} from a JSON file on the classpath
     * and returns them as a {@link Set}.
     *
     * @param filename the name of the JSON file in {@code src/main/resources} with all the Title Deeds
     * @return an unmodifiable {@link Set} of {@link TitleDeed}, never {@code null}
     * @throws IOException if the resource is missing or cannot be parsed
     */
    public static Set<TitleDeed> loadTitleDeed(final String filename) throws IOException {
        try (InputStream is = getRequiredStream(filename)) {
            var array = MAPPER.readValue(is, TitleDeed[].class);
            return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(array)));
        }
    }

    /**
     * Reads a text resource from the classpath into a single {@link String}.
     * <p>
     * @param filename the name of the text file in {@code src/main/resources} with all the game rules
     * @return the full contents of the file (using {@code UTF-8})
     */
    public static String loadTextResource(final String filename) {
        try (var is = getRequiredStream(filename);
             var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Helper that opens a classpath resource as an InputStream,
     * @throws IOException if the file is missing
     */
    private static InputStream getRequiredStream(final String filename) throws IOException {
        var stream = Thread.currentThread()
                           .getContextClassLoader()
                           .getResourceAsStream(filename);
        if (stream == null) {
            throw new IOException("Resource not found: " + filename);
        }
        return stream;
    }
}
