package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.api.chancesAndCommunityChest.api.BaseInterpreterInt;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of base interpreter.
 */
public final class BaseInterpreter implements BaseInterpreterInt {

    private final List<BaseCommand> baseCommands;
    private final BaseCommandFactory factory = new BaseCommandFactoryImpl();

    /**
     * constructor.
     * @param baseCommands the list o the base command supportetd by the game
     */
    public BaseInterpreter(final List<BaseCommand> baseCommands) {
        this.baseCommands = List.copyOf(baseCommands);
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
