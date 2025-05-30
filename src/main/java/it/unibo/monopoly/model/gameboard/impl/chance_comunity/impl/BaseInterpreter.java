package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public class BaseInterpreter implements Interpreter {

    private List<BaseCommand> baseCommands = new LinkedList<>();
    private ArgsInterpreter argsInterpreter = new ArgsInterpreterImpl(); 
    private BaseCommandFactory factory = new BaseCommandFactoryImpl();

    public BaseInterpreter(final List<BaseCommand> baseCommands){
        this.baseCommands = baseCommands;
    }

    @Override
    public Command interpret(String toInterpretString, Board board, TurnationManager turnM) {
        Command comm = factory.still(); 
        ParserOnColon pars = new ParserOnColon(toInterpretString);
        String comString = pars.next();
        Optional<BaseCommand> com = baseCommands.stream().filter(p -> p.getKeyWord().equals(comString)).findAny();
        if (com.isPresent()) {
            BaseCommand base = com.get(); 
            if (pars.hasNesxt()) {
                argsInterpreter.interpret(pars.next(), base, board, turnM);
            }
            comm = base;
        }
        return comm;
    }

}
