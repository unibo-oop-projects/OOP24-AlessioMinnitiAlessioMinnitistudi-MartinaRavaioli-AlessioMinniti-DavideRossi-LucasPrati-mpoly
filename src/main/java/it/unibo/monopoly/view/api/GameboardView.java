package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
    * board view interface.
*/
public interface GameboardView extends GamePanel {
    /**
     * add players' houses.
     * @param prop curr property
     * @param numHouses property's num houses
    */
    void addHouse(Property prop, int numHouses);
    /**
     * add players' hotel.
     * @param prop curr property
    */
    void addHotel(Property prop);
    /**
     * change the positions.
     * @param currPlayer curr player
     * @param newPos new position
    */
    void changePos(int currPlayer, Position newPos);
    /**
     * set new bought properties.
     *  @param prop property
     *  @param currPlayer curr player
    */
    void buyProperty(Property prop, int currPlayer);
    /**
     * clear the panel.
     * @param prop property to clear
    */
    void clearPanel(Property prop);
}
