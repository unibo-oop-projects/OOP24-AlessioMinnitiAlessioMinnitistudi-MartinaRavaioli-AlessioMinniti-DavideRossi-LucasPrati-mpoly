package it.unibo.monopoly.utils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import it.unibo.monopoly.utils.api.UseFileTxt;


/**
 * Implementation of {@link UseFileTxt} for loading text-based resources.
 * <p>
 * It handles I/O errors gracefully by returning the exception message instead of failing.
 * <p>
 * Inherits file access utilities from {@link AbstractUseFileImpl}.
 */
public class UseFileTxtImpl extends AbstractUseFileImpl implements UseFileTxt{

    /**
     * {@inheritDoc}
     */
    @Override
    public String loadTextResource(String path) {
        try (BufferedReader reader = getRequiredReader(path)) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return e.getMessage();
        }
    }
    
}
