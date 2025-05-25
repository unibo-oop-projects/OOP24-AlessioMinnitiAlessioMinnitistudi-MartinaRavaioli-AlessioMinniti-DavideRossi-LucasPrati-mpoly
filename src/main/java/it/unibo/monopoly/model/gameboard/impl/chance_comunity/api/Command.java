package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

public interface Command {

    /**
     * execute the action expressed in the command.
     */
    void execute();

    /**
     * 
     * @return the keword for the command.
     */
    String getKeyWord();
}
