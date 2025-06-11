package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * a factory for base command supported by the game.
 */
public interface BaseCommandFactory {

    /**
     * a default command that does nothing.
     * @return the command
     */
    BaseCommand still();
    /**
     * a method that create a list of all the command supported.
     * @return a list of all the possible base command
     * @param bank to execute some command
     * @param turnM to execute some commands 
     * @param board to execute some command
     */
    List<BaseCommand> allCommand(Bank bank, Board board, TurnationManager turnM);

}
