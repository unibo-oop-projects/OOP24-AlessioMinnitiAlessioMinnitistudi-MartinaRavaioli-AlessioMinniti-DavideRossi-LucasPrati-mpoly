package it.unibo.monopoly.utils.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;


/**
 * Represents a generic file loader capable of opening and accessing resources from the classpath.
 * <p>
 * This is a generic marker interface for internal use.
 * Clients should use specific interfaces like {@link UseFileTxt}, {@link UseFileConfiguration}, {@link UseFileJson}.
 */
public interface UseFile {

    /**
     * Loads a resource from the classpath as an {@link InputStream}.
     * 
     * @param path relative path of the resource file
     * @return an {@link InputStream} for the specified resource
     * @throws IOException if the resource cannot be found or loaded
     * @throws NullPointerException if {@code path} is {@code null}
     */
    InputStream getRequiredStream(final String path) throws IOException;


    /**
     * Loads a resource from the classpath and returns a buffered reader over its contents.
     * 
     * @param path relative path of the resource file
     * @return a {@link BufferedReader} to read the resource
     * @throws IOException if the resource cannot be found
     * @throws NullPointerException if {@code path} is {@code null}
     */
    BufferedReader getRequiredReader(final String path) throws IOException;

}
