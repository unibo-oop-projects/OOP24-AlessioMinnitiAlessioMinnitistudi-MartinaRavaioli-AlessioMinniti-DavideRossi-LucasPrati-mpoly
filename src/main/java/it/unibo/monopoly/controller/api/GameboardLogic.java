package it.unibo.monopoly.controller.api;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.impl.Type;

/**
    * board logic interface.
*/
public interface GameboardLogic {
    /**
    * control if the tile created is on the board.
    */
    boolean isBoardTile(int i, int j, int size);

    /**
    * board view interface.
    */
    Color getTileColor(Type type);

}
