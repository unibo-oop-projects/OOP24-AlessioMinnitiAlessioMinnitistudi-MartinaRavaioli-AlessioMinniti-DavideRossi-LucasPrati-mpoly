package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of interpreter for complex command that are composed of multiple base commands.
 */
public final class ComplexInterpreter implements Interpreter {

    private final BaseCommandFactory factory = new BaseCommandFactoryImpl();

    private final BaseInterpreter inter;


    public ComplexInterpreter(final Board board, final Bank bank) {
        inter = new BaseInterpreter(factory.allCommand(bank, board));
    }

    @Override
    public Command interpret(final String toInterpretString, final Board board, final TurnationManager turnM) {
        List<Command> commands = new LinkedList<>(); 
        final ParserOnNewLine pars = new ParserOnNewLine(toInterpretString);
        while (pars.hasNesxt()) {
            commands.add(inter.interpret(pars.next(), board, turnM));            
        }
        final Command comm = new ComplexCommand(commands, toInterpretString);
        return comm;
    }

}
