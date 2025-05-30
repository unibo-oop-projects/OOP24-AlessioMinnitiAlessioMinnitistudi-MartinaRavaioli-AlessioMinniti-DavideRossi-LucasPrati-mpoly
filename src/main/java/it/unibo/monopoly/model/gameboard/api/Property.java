package it.unibo.monopoly.model.gameboard.api;

/**
* property interface.
*/
public interface Property extends Tile {

    /**
     * add house.
    */
    void buildHouse();

    /**
     * add hotel.
    */
    void buildHotel();

    /**

     * get the number of houses.
     * @return int
    */
    int getNHouses();

    /**
     * control if it has an hotel.
     * @return bool
    */
    boolean hasHotel();

    boolean canBuildHouse();

    boolean canBuildHotel();
}
