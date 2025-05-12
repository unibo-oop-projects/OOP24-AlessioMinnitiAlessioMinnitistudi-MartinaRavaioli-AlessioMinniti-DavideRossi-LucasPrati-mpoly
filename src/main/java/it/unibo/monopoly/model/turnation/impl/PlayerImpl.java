package it.unibo.monopoly.model.turnation.impl;

import java.util.Optional;

import it.unibo.monopoly.model.turnation.api.Parkable;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.Position;
import it.unibo.monopoly.model.turnation.api.Prisonable;
import java.awt.Color;
import java.util.Objects;

import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.utils.Identifiable;

/**
 * Player implementation.
*/

public final class PlayerImpl implements Player {

    private final int id;
    private final String name;
    private final Color color;    
    Optional<Prisonable> prison;
    Optional<Parkable> parking;

    /**
     * Private constructor used internally by the static factory method {@link #of(int, String, Color)}.
     *
     * @param id the {@link Identifiable} representing the player
     * @param name  the name chosen by the player
     * @param color the {@link Color} representing the player
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
     * @param id the {@link Identifiable} representing the player
     * @param name the name chosen by the player
     * @param color the {@link Color} representing the player
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

    @Override
    public boolean isAlive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
    }

    @Override
    public boolean isParked() {
        if (parking.isPresent()) {
            return parking.get().isParked();
        }else{
            return false;
        }
    }

    @Override
    public void park() {
        if (parking.isPresent()) {
            parking.get().park();
        }
    }

    @Override
    public boolean isInPrison() {
        if (prison.isPresent()) {
            return prison.get().isInPrison();
        }else{
            return false;
        }
    }

    @Override
    public void putInPrison() {
        if (prison.isPresent()) {
            prison.get().putInPrison();
        }
    }
   
}
