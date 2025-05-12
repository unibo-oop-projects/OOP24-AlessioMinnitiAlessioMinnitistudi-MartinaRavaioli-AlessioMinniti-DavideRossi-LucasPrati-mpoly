package it.unibo.monopoly.resources;
/**
 * identifiable interface.
 * @param <E>
*/
public interface Identifiable<E> {
    /**
     * get ID.
     * @return E
    */
    E getID();
    /**
     * set ID.
     * @param value
    */
    void setID(E value);
}

