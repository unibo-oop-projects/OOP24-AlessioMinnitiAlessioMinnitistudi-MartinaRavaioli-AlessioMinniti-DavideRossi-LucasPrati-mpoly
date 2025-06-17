package it.unibo.monopoly.view.api;

import java.awt.Color;

import it.unibo.monopoly.model.turnation.api.Position;

/**
    * board view interface.
*/
public interface GameboardView extends GamePanel {
    /**
     * add players' houses.
     * @param propName name of the property
     * @param color property's color
     * @param numHouses property's num houses
    */
    void addHouse(String propName, Color color, int numHouses);
    /**
     * add players' hotel.
     * @param propName property's name
     * @param color property's color
    */
    void addHotel(String propName, Color color);
    /**
     * change the positions.
     * @param currPlayer curr player
     * @param newPos new position
    */
    void changePos(int currPlayer, Position newPos);
    /**
     * set new bought properties.
     *  @param propName property name
     *  @param playerColor color of the player
    */
    void buyProperty(String propName, Color playerColor);
    /**
     * clear the panel.
     * @param name property to clear
    */
    void clearPanel(String name);
    /**
     * remove house from the property.
     * @param prop property
     * @param numHouses new number of houses
     */
    void removeHouse(String propName, int numHouses, Color color);
    /**
     * remove hotel from the property.
     * @param prop property
     */
    void removeHotel(String propName, Color color);
    /**
     * delete the player.
     * @param color color of the player
     * @param id of the player
     */
    void deletePlayer(Color color, int id);
    /**
     * clear all the panels.
     */
    void clearAll();
}
