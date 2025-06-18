package it.unibo.monopoly.model.gameboard.api.chances_communiy.api;

import it.unibo.monopoly.model.turnation.api.Player;

/**
 * interface for generic command.
 */
public interface Command {

    /**
     * execute the action expressed in the command.
     * @param player on wich the command is executed
     * @param args used to execute the command
     */
    void execute(Player player, String args);

    /**
     * get the unic keyword of the command.
     * @return the keword for the command
     */
    String getKeyWord();

    /**
     * get a description in natural language of what the command does.
     * @return the description
     */
    String getDesc();

}
