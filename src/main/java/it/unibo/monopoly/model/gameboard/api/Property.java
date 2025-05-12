package it.unibo.monopoly.model.gameboard.api;

/**
 * property interface.
*/
public interface Property extends Tile {
    /**
     * add house.
    */
    void buildHouse() throws Exception;

    /**
     * add hotel.
    */
    void buildHotel() throws Exception;

}
