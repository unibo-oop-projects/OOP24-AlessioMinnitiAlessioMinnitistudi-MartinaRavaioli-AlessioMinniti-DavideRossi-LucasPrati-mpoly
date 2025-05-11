package it.unibo.monopoly.model.gameboard.api;

import java.awt.Color;

import it.unibo.monopoly.model.turnation.api.Position;

public interface Pawn {

    void move(int steps);

    Position getPosition();

    Color getColor();

}
