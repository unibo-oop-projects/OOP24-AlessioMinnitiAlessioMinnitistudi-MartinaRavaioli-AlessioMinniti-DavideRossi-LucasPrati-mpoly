package it.unibo.monopoly.view.api;

import it.unibo.monopoly.model.gameboard.api.Pawn;
import it.unibo.monopoly.model.gameboard.api.Property;
import it.unibo.monopoly.model.turnation.api.Position;

/**
    * board view interface.
*/
public interface GameboardView extends GamePanel{
    /**
     * add houses' players.
    */
    void addHouse();
    /**
     * add hotels' players.
    */
    void addHotel();
    /**
     * change the positions.
     * @param currPlayers
     * @param newPos
    */
    void changePos(int currPlayer, Position newPos);
    /**
     * set new bought properties.
     *  @param prop
     *  @param currPlayer
    */
    void buyProperty(Property prop, Pawn currPlayer);
}
