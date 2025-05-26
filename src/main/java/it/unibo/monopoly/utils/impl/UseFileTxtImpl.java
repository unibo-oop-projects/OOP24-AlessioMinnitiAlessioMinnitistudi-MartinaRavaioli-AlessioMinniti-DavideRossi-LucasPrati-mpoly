package it.unibo.monopoly.utils.impl;


import java.io.BufferedReader;
import java.io.IOException;

import java.util.stream.Collectors;

import it.unibo.monopoly.utils.api.UseFileTxt;


/**
 * Implementation of {@link UseFileTxt} that provides functionality
 * to read plain text resources from the classpath.
 * <p>
 * It handles I/O errors gracefully by returning the exception message instead of failing.
 * <p>
 * Inherits file access utilities from {@link AbstractUseFileImpl}.
 */
public final class UseFileTxtImpl extends AbstractUseFileImpl implements UseFileTxt {

    /**
     * {@inheritDoc}
     * <p>
     * If the resource cannot be loaded, returns the exception message instead of throwing.
     */
    @Override
    public String loadTextResource(final String path) {
        try (BufferedReader reader = getRequiredReader(path)) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return e.getMessage();
        }
    }

}
