package it.unibo.monopoly.controller.api;


/**
    * board logic interface.
*/
public interface GameboardLogic {
    /**
    * control if the tile created is on the board.
    * @param i col
    * @param j row
    * @param size size
    * @return bool
    */
    boolean isBoardTile(int i, int j, int size);

    /**
    * control if the tile is a card.
    * @param i col
    * @param j row
    * @param size size
    * @return int
    */
    int tileCard(int i, int j, int size);

    /**
     * get the size of the board.
     * @param numTiles tiles' number
     * @return int
    */
    int getSize(int numTiles);
}
