package it.unibo.monopoly.utils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import it.unibo.monopoly.utils.api.UseFile;

/**
 * Abstract base implementation of the {@link UseFile} interface, providing common logic 
 * for opening classpath resources as streams or readers.
 * <p>
 * This implementation handles null checking and ensures that missing resources
 * are reported clearly with {@link IOException}.
 */
abstract class AbstractUseFileImpl implements UseFile {

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getRequiredStream(String path) throws IOException {
        Objects.requireNonNull(path, "path must not be null");
        final InputStream stream = ClassLoader.getSystemResourceAsStream(path);
        if (stream == null) {
            throw new IOException("Resource not found: " + path);
        }
        return stream;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedReader getRequiredReader(String path) throws IOException {
        return new BufferedReader(new InputStreamReader(getRequiredStream(path), StandardCharsets.UTF_8));
    }
    
}
