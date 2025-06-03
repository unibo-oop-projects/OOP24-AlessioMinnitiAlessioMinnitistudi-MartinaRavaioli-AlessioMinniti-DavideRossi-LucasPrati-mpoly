package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseInterpreterInt;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of base interpreter.
 */
public final class BaseInterpreter implements BaseInterpreterInt {

    private List<BaseCommand> baseCommands = new LinkedList<>();
    private ArgsInterpreter argsInterpreter = new ArgsInterpreterImpl(); 
    private BaseCommandFactory factory = new BaseCommandFactoryImpl();

    /**
     * constructor.
     * @param baseCommands the list o the base command supportetd by the game
     */
    public BaseInterpreter(final List<BaseCommand> baseCommands) {
        this.baseCommands = baseCommands;
    }

    @Override
    public BaseCommand interpret(final String toInterpretString, final Board board, final TurnationManager turnM) {
        BaseCommand comm = factory.still(); 
        final ParserOnColon pars = new ParserOnColon(toInterpretString);
        final String comString = pars.next();
        final Optional<BaseCommand> com = baseCommands.stream().filter(p -> p.getKeyWord().equals((String) comString)).findAny();
        if (com.isPresent()) {
            final BaseCommand base = com.get(); 
            if (pars.hasNesxt()) {
                argsInterpreter.interpret(pars.next(), base, board, turnM);
            }
            comm = base;
        }
        return comm;
    }

}
