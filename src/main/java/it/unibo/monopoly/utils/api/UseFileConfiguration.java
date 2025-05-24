package it.unibo.monopoly.utils.api;

import it.unibo.monopoly.utils.Configuration;

/**
 * Specialization of {@link UseFile} for loading a {@link Configuration} object
 * from a custom key-value configuration file.
 * <p>
 * The file must follow a simple {@code KEY: VALUE} format, with optional comments and blank lines.
 */
public interface UseFileConfiguration extends UseFile {

    /**
     * Loads a {@link Configuration} object from a configuration file in the classpath.
     * Skips malformed or unknown entries gracefully.
     * 
     * @param path relative path of the resource
     * @return a {@link Configuration} object, or a default one if loading fails
     */
    Configuration loadConfigurationFile(final String path);
}
