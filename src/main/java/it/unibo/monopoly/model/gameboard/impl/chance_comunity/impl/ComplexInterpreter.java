package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.Command;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.Interpreter;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of interpreter for complex command that are composed of multiple base commands.
 */
public final class ComplexInterpreter implements Interpreter {

    private final BaseInterpreter inter;


    /**
     * constructor.
     * @param board to execute some commands
     * @param bank to execute some commands
     * @param turnM to execute some commands 
     */
    public ComplexInterpreter(final Board board, final Bank bank, final TurnationManager turnM) {
        inter = new BaseInterpreter(board, bank, turnM);
    }

    @Override
    public Command interpret(final String toInterpretString, final Board board, final TurnationManager turnM) {
        final List<Pair<BaseCommand, String>> commands = new LinkedList<>(); 
        final ParserOnNewLine pars = new ParserOnNewLine(toInterpretString);
        while (pars.hasNesxt()) {
            final ParserOnColon parOnColon = new ParserOnColon(pars.next());
            final BaseCommand co = inter.interpret(parOnColon.next(), board, turnM);
            StringBuilder b = new StringBuilder("");
            while (parOnColon.hasNesxt()) {
                b = b.append(parOnColon.next());
            }
            final Pair<BaseCommand, String> com = new Pair<>(co, b.toString());
            commands.add(com);
        }
        return new ComplexCommand(commands, toInterpretString);
    }

}
