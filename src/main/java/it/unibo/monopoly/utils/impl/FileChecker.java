package it.unibo.monopoly.utils.impl;


import java.io.IOException;
import java.io.InputStream;


/**
 * Utility class that provides static methods for verifying the existence and accessibility
 * of resource files located in the application's classpath.
 * <p>
 * This class is stateless and not meant to be instantiated.
 */
public final class FileChecker {

    private FileChecker() { /* Prevent instantiation */ }

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

        try (InputStream ignored = ClassLoader.getSystemResourceAsStream(path)) {
            return true;
        } catch (final IOException e) {
            return false;
        }
    }
    
}
