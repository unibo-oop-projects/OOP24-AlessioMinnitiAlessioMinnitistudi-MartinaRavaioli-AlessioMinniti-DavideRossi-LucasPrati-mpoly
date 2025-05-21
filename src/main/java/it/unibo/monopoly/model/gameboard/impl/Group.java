package it.unibo.monopoly.model.gameboard.impl;

import java.awt.Color;
/**
 * enum type.
*/
public enum Group {
    /**
     * red.
    */
    RED,
    /**
     * blue.
    */
    BLUE,
    /**
     * green.
    */
    GREEN,
    /**
     * yellow.
    */
    YELLOW,
    /**
     * purple.
    */
    PURPLE,
    /**
     * orange.
    */
    ORANGE,
    /**
     * cyan.
    */
    CYAN,
    /**
     * black.
    */
    BLACK,
    /**
     * station.
    */
    STATION,
    /**
     * society.
    */
    SOCIETY,
    /**
     * special.
    */
    SPECIAL;

    /**
     * method to get the real color from the type of the enum.
     * @return Color
    */
    public Color getColor() {
        switch (this) {
            case RED -> {
                return Color.RED;
            }
            case BLUE -> {
                return Color.BLUE;
            }
            case GREEN -> {
                return Color.GREEN;
            }
            case YELLOW -> {
                return Color.YELLOW;
            }
            case PURPLE -> {
                return Color.MAGENTA;
            }
            case ORANGE -> {
                return Color.ORANGE;
            }
            case CYAN -> {
                return Color.CYAN;
            }
            case BLACK -> {
                return Color.BLACK;
            }
            default -> {
                return Color.WHITE;
            }
        }
    }
}
