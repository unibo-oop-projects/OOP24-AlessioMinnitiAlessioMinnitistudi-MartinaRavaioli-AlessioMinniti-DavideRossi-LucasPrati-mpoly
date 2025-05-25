package it.unibo.monopoly.model.gameboard.impl;

/**
 * Pair Class.
 * @param <X> type of the first value
 * @param <Y> type of the second value
 */
public final class Pair<X, Y> {

    private final X a;
    private final Y b;

    /**
     * constructor for class Pair.
     * assing the 2 value
     * @param a
     * @param b
     */
    public Pair(final X a, final Y b) {
        this.a = a;
        this.b = b;
    }

    /**
     * getter.
     * @return the first value
     */
    public X a() {
        return this.a;
    }

    /**
     * getter.
     * @return the second value
     */
    public Y b() {
        return this.b;
    }
}
