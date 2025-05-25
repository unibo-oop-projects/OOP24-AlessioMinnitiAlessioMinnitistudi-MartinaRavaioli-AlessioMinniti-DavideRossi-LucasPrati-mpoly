package it.unibo.monopoly.utils.api;


import it.unibo.monopoly.utils.impl.Configuration;


/**
 * Specialization of {@link UseFile} for loading configuration key-value pairs.
 * <p>
 * Expected format: each line must follow the {@code KEY: VALUE} pattern.
 * Lines that are blank or start with {@code #} are ignored.
 */
public interface UseConfigurationFile extends UseFile {

    /**
     * Loads a {@link Configuration} object from a configuration file in the classpath.
     * Skips malformed or unknown entries gracefully.
     * 
     * @param path relative path of the resource
     * @return a {@link Configuration} object, or a default one if loading fails
     */
    Configuration loadConfigurationFile(final String path);
}
