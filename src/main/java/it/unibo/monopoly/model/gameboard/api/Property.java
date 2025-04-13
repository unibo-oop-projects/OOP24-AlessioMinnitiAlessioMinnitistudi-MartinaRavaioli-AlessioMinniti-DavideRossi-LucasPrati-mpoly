package it.unibo.monopoly.model.gameboard.api;

/**
 * property interface.
*/
public interface Property extends Tile {

    //get the price
    /**
     * @return price
     */
    int getRent();

    void buildHouse() throws Exception;

    void buildHotel() throws Exception;

    int getPrice();

    int getHousePrice();

    int getHotelPrice();
}
