package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * Player implementation.
*/
public final class PlayerImpl implements Player {

    private final String name;
    private final Color color;

    /**
     * Private constructor. Use {@link #of(String, Color)} to create instances.
     */
    private PlayerImpl(final String name, final Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public final Color getColor() {
        return color;
    }

    @Override
    public final String getName() {
        return name;
    }


    /**
     * Static factory method for creating a new {@link PlayerImpl} instance.
     * <p>
     * This method applies the Factory Method pattern (static variant),
     * allowing centralized creation logic and possible future extensions.
     *
     * @param name the nickname chosen by the player
     * @param color the color representing the player
     * @return a new {@link Player} instance
     * @throws IllegalArgumentException if name is null/empty or color is null
     */
    public static Player of(final String name, final Color color) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }
        if (color == null) {
            throw new IllegalArgumentException("Player color cannot be null.");
        }
        return new PlayerImpl(name, color);
    }

}
