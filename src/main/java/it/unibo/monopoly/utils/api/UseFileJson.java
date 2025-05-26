package it.unibo.monopoly.utils.api;


import java.io.UncheckedIOException;

import java.util.List;
import java.util.Set;

import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;


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


    /**
     * Loads a set of {@link TitleDeed} instances from a JSON file in the classpath.
     * <p>
     * This method internally loads a list of {@link BaseTitleDeed} objects and safely upcasts them
     * to the interface {@link TitleDeed}, returning them as an unmodifiable {@link Set}.
     * <p>
     * The JSON file is expected to contain an array of objects, each compatible with the structure of {@link BaseTitleDeed},
     * and must be located in the resource path relative to the classpath root.
     *
     * @param path the relative path of the JSON resource file
     * @return an unmodifiable {@link Set} of {@link TitleDeed} instances
     * @throws NullPointerException if {@code path} is {@code null}
     * @throws UncheckedIOException if the file cannot be read or parsed
     */
    Set<TitleDeed> loadTitleDeeds(String path);
}
