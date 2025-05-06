package it.unibo.monopoly.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * A small utility class to load resources {@code .txt } from the classpath.
 */
public final class UseFileTxt {

    private final String extractedText;

    public UseFileTxt(final String filename) {
        this.extractedText = loadTextResource(filename);
    }

    /**
     * @return the text extracted from the resource
     */
    public String getExtractedText() {
        return extractedText;
    }

    /**
     * Reads a text resource from the classpath into a single {@link String}.
     * <p>
     * @param filename the name of the text file in {@code src/main/resources} with all the rules of the game
     * @return a {@link String} with the full contents of the file (using {@code UTF-8})
     */
    private String loadTextResource(final String filename) {
        try (var is = getRequiredStream(filename);
             var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return e.getMessage();
        }
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
}
