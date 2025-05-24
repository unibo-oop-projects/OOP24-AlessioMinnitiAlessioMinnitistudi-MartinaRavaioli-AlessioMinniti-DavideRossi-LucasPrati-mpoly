package it.unibo.monopoly.utils.api;

/**
 * Specialization of {@link UseFile} for reading text-based resources.
 */
public interface UseFileTxt extends UseFile {
    
    /**
     * Reads a text resource from the classpath and returns its contents as a single string.
     * 
     * @param path relative path of the resource
     * @return a {@link String} containing the full contents of the file,
     *         or the error message if the resource could not be loaded
     */
    String loadTextResource(final String path);
}
