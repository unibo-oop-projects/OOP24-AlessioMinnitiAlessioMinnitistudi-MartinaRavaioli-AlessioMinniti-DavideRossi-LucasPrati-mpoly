package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.BaseInterpreterInt;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of base interpreter.
 */
public final class BaseInterpreter implements BaseInterpreterInt {

    private final List<BaseCommand> baseCommands;
    private final BaseCommandFactory factory = new BaseCommandFactoryImpl();

    /**
     * constructor.
     * @param bank to get the list of possible base commands
     * @param board to get the list of possible base commands
     * @param turnM to get the list of possible base commands
     */
    public BaseInterpreter(final Board board, final Bank bank, final TurnationManager turnM) {
        this.baseCommands = factory.allCommand(bank, board, turnM);
    }

    @Override
    public BaseCommand interpret(final String toInterpretString, final Board board, final TurnationManager turnM) {
        BaseCommand comm = factory.still(); 
        final ParserOnColon pars = new ParserOnColon(toInterpretString);
        final String comString = pars.next();
        final Optional<BaseCommand> com = baseCommands.stream().filter(p -> p.getKeyWord().equals(comString)).findAny();
        if (com.isPresent()) {
            comm = com.get();
        }
        return comm;
    }

}
