package it.unibo.monopoly.model.gameboard.api;

/**
* property interface.
*/
public interface Property extends Tile {
    /**
     * tells if in this property you can build houses and hotel.
     * @return bool
    */
    boolean isBuildable();

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

    /**
     * tells if you can build an house.
     * @return bool
    */
    boolean canBuildHouse();
    /**
     * tells if you can build an hotel.
     * @return bool
    */
    boolean canBuildHotel();
}
