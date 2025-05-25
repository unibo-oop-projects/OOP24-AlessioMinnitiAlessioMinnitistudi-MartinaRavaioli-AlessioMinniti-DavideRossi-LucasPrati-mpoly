package it.unibo.monopoly.controller.api;

import java.awt.Color;

import it.unibo.monopoly.model.gameboard.impl.Type;

/**
    * board logic interface.
*/
public interface GameboardLogic {
    /**
    * control if the tile created is on the board.
    * @param i
    * @param j
    * @param size
    */
    boolean isBoardTile(int i, int j, int size);

    /**
    * control if the tile is a card.
    * @param i
    * @param j
    * @param size
    */
    int isCard(int i, int j, int size);

}
