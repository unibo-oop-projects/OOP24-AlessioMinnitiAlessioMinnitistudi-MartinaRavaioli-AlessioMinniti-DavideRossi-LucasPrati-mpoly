package it.unibo.monopoly.controller.api;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.impl.Type;

public interface GameboardLogic {
    boolean isBoardTile(int i, int j, int size);

    Color getColor(Type type);
}
