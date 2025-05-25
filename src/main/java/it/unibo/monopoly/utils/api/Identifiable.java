package it.unibo.monopoly.utils.api;

/**
 * Generic interface for Identification.
 * @param <E> identifiable type
 */
public interface Identifiable<E> {
    /**
     * @return the ID value
     */
    E getID();
}
