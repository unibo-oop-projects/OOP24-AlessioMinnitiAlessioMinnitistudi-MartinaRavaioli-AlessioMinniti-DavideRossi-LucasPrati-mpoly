package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.turnation.api.Player;

public interface Command {

    /**
     * execute the action expressed in the command.
     * @param player on wich the command is executed
     */
    void execute(Player player);

    /**
     * 
     * @return the keword for the command.
     */
    String getKeyWord();

}
