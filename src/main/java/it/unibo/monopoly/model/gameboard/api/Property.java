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
}
