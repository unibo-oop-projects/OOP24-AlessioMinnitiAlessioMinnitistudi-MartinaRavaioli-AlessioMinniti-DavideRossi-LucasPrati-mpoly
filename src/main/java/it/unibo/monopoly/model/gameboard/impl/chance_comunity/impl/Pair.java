package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

/**
 * Pair class, keeps 2 generic values and has getter methods.
 * @param <X> type of the first value
 * @param <Y> type of the second value
 */
public final class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * constructor.
     * @param x first value
     * @param y second value
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for first value.
     * @return first value
     */
    public X getX() {
        return this.x;
    }

    /**
     * getter for second value.
     * @return second value
     */
    public Y gety() {
        return this.y;
    }
}
