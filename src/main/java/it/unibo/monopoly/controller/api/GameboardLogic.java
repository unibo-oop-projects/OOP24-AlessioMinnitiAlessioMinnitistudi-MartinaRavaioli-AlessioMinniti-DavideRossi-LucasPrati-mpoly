package it.unibo.monopoly.controller.api;


/**
    * board logic interface.
*/
public interface GameboardLogic {
    /**
    * control if the tile created is on the board.
    * @param i
    * @param j
    * @param size
    * @return bool
    */
    boolean isBoardTile(int i, int j, int size);

    /**
    * control if the tile is a card.
    * @param i
    * @param j
    * @param size
    * @return int
    */
    int isCard(int i, int j, int size);

}
