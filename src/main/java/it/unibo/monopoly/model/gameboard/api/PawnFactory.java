package it.unibo.monopoly.model.gameboard.api;

import java.awt.Color;

import it.unibo.monopoly.model.turnation.api.Position;

public interface PawnFactory {
    Pawn createBasic(int id, Position pos, Color color);

    Pawn createAdvanced(int id, Position pos, Color color, String shape);
}
