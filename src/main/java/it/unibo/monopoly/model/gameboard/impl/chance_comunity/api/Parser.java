package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

/**
 * interface for a generic parser.
 */
public interface Parser {

    /**
     * this method tells if the string has come to its end.
     * @return wether or not it continues
     */
    boolean hasNesxt();

    /**
     * this method get the next substring based on the type of parser.
     * @return the string
     */
    String next();

}
