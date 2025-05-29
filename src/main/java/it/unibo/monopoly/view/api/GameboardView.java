package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
    * board view interface.
*/
public interface GameboardView extends GamePanel {
    /**
     * add players' houses.
     * @param prop
     * @param numHouses
    */
    void addHouse(Property prop, int numHouses);
    /**
     * add hotels' players.
    */
    void addHotel();
    /**
     * change the positions.
     * @param currPlayer
     * @param newPos
    */
    void changePos(int currPlayer, Position newPos);
    /**
     * set new bought properties.
     *  @param prop
     *  @param currPlayer
    */
    void buyProperty(Property prop, int currPlayer);
    /**
     * clear the panel.
    */
    void clearPanel();
}
