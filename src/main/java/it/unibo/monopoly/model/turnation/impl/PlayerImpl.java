package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;
import java.util.Objects;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * Player implementation.
*/
public final class PlayerImpl implements Player {

    private final int id;
    private final String name;
    private final Color color;

    /**
     * Private constructor used internally by the static factory method {@link #of(String, Color)}.
     *
     * @param name  the name chosen by the player
     * @param color the color representing the player
     */
    private PlayerImpl(final int id, final String name, final Color color) {
        this.id = id;
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

    @Override
    public Integer getID() {
        return id;
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
    public static Player of(final int id, String name, final Color color) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(id);
        Objects.requireNonNull(color);
        if (name.isBlank()) {
            name = "Player " + Integer.toString(id);
        }
        return new PlayerImpl(id, name, color);
    }

}
