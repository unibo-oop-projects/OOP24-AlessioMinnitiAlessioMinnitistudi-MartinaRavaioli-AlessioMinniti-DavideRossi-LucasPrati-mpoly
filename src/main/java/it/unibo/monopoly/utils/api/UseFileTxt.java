package it.unibo.monopoly.utils.api;


/**
 * Specialization of {@link UseFile} for loading plain text files.
 * <p>
 * The file is expected to contain textual content encoded in UTF-8.
 */
public interface UseFileTxt extends UseFile {

    /**
     * Reads a text resource from the classpath and returns its contents as a single string.
     * <p>
     * Handle IO errors returning the exception message instead of throwing.
     * @param path relative path of the resource
     * @return a {@link String} containing the full contents of the file,
     *         or the error message if the resource could not be loaded
     */
    String loadTextResource(String path);
}
