package it.unibo.monopoly.model.gameboard.api;

import it.unibo.monopoly.model.gameboard.impl.Type;

/**
 * tile interface.
 */

public interface Tile {
    public Type getType();

    public void setType(Type type);
}
