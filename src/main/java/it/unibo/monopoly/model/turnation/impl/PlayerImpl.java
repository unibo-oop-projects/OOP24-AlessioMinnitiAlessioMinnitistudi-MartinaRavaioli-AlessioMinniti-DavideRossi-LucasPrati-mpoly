package it.unibo.monopoly.model.turnation.impl;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.Player;
import java.awt.Color;
import java.util.Collection;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerImpl other = (PlayerImpl) obj;
        if (id != other.id) {
            return false;
        } 
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (color == null) {
            if (other.color != null) {
                return false;
            }
        } else if (!color.equals(other.color)) {
            return false;
        }
        return true;
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
    @Override
    public boolean isAlive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
    }

    @Override
    public boolean isParked() {
        return true;
    }

    @Override
    public void park() {
    }

    @Override
    public boolean isInPrison() {
        return true;
    }

    @Override
    public void putInPrison() {
    }

    @Override
    public boolean canExitPrison(final Collection<Integer> dices, final Board board) {
        return true;
    }
}
