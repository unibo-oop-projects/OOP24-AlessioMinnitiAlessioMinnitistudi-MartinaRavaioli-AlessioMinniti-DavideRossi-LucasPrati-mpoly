package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl.BaseCommand;
import it.unibo.monopoly.model.transactions.api.Bank;

public interface BaseCommandFactory {
    /**
     * 
     * @return a list of all the possible base command.
     */
    List<BaseCommand> allCommand(Bank bank, Board board);

}
