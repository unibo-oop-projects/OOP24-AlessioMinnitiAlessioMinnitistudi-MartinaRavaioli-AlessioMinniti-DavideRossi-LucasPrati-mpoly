package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * Player implementation.
*/
public class PlayerImpl implements Player {

    private final String name;
    private final Color color;

    /**
     * Creates a new Player with a name and a color that represents him.
     * @param name The nickname chosen by the player for himself
     * @param color The color of the player
     */
    public PlayerImpl(final String name, final Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String getName() {
        return name;
    }

}