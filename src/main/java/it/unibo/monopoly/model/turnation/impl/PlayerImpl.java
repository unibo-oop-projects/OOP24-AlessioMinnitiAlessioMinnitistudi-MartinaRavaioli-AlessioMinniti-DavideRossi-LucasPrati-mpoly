package it.unibo.monopoly.model.turnation.impl;

import java.awt.Color;
import java.util.Objects;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.utils.Identifiable;

/**
 * Player implementation.
*/
public final class PlayerImpl implements Player {

    private final int id;
    private final String name;
    private final Color color;

    /**
     * Private constructor used internally by the static factory method {@link #of(int, String, Color)}.
     *
     * @param id the {@link Identifiable} representing the {@link Player}
     * @param name the {@code name} chosen by the {@link Player} for himself
     * @param color the {@link Color} representing the {@link Player}
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

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public void makeMove(final int steps) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeMove'");
    }

    @Override
    public boolean isAlive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
    }

    /**
     * Static factory method for creating a new {@link PlayerImpl} instance.
     * 
     * This method applies the Factory Method pattern (static variant).
     * 
     * @param id the {@link Identifiable} representing the {@link Player}
     * @param name the {@code name} chosen by the {@link Player} for himself
     * @param color the {@link Color} representing the {@link Player}
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
