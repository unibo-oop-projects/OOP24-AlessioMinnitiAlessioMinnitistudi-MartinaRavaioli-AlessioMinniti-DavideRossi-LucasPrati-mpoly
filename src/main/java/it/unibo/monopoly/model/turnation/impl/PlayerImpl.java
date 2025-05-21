package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;
import java.util.Objects;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.utils.Identifiable;

/**
 * {@link Player}'s implementation.
*/
public final class PlayerImpl implements Player {

    private final int id;
    private final String name;
    private final Color color;

    /**
     * Private constructor used internally by the static factory method {@link #of(int, String, Color)}.
     *
     * @param id the {@link Identifiable} representing the {@link Player}
     * @param name the name chosen by the {@link Player} for himself
     * @param color the {@link Color} representing the {@link Player}
     */
    private PlayerImpl(final int id, final String name, final Color color) {
        this.id = id;
        this.name = name;
        this.color = color;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getID() {
        return id;
    }

    /**
     * Static factory method for creating a new {@link PlayerImpl} instance.
     * <p>
     * If the provided {@code name} is blank or contains only whitespace, it will be replaced with a default
     * value in the format {@code "Player <id>"}, where {@code id} is the player's unique identifier.
     * Duplicate names are permitted; identity is enforced via {@code id}.
     * 
     * @param id the id the unique player ID
     * @param name name the player's nickname (may be blank)
     * @param color the player's color
     * @return a new {@link Player} instance
     * @throws NullPointerException if {@code id}, {@code name} or {@code color} are {@code null}
     */
    public static Player of(final int id, final String name, final Color color) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(id);
        Objects.requireNonNull(color);
        if (name.isBlank()) {
            return new PlayerImpl(id, "Player " + Integer.toString(id), color);
        }
        return new PlayerImpl(id, name, color);
    }

}
