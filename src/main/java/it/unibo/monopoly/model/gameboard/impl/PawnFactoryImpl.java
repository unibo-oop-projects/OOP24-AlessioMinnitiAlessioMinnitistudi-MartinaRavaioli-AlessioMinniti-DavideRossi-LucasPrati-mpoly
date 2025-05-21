package it.unibo.monopoly.model.gameboard.impl;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.PawnFactory;
import it.unibo.monopoly.model.turnation.api.Position;

/**
 * pawn factory implementation.
*/
public final class PawnFactoryImpl implements PawnFactory {

    @Override
    public Pawn createBasic(final int id, final Position pos, final Color color) {
        return new PawnImpl(id, pos, color);
    }

    @Override
    public Pawn createAdvanced(final int id, final Position pos, final Color color, final String shape) {
        return new PawnImpl(id, pos, color, shape);
    }
}
