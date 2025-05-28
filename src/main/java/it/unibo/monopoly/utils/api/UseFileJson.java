package it.unibo.monopoly.utils.api;


import java.io.UncheckedIOException;

import java.util.List;


/**
 * Specialization of {@link UseFile} for reading and deserializing JSON files.
 */
public interface UseFileJson extends UseFile {

    /**
     * Loads a list of objects of the specified type from a JSON file in the classpath.
     * <p>
     * The file must contain a JSON array of elements compatible with the provided {@code type}.
     * 
     * @param <T> the type of elements to load
     * @param path the relative path of the JSON resource file
     * @param type the class of the target type
     * @return a {@link List} of deserialized objects of type {@code T}
     * @throws NullPointerException if {@code path} or {@code type} is {@code null}
     * @throws UncheckedIOException if the file cannot be read or parsed
     */
    <T> List<T> loadJsonList(String path, Class<T> type);
}
