package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;

public class BaseInterpreter implements Interpreter {

    private List<BaseCommand> baseCommands = new LinkedList<>();
    private ArgsInterpreter argsInterpreter = new ArgsInterpreterImpl(); 

    public BaseInterpreter(final List<BaseCommand> baseCommands){
        this.baseCommands = baseCommands;
    }

    @Override
    public Command interpret(String toInterpretString) {
        Command comm = null; 


        return comm;
    }

}
