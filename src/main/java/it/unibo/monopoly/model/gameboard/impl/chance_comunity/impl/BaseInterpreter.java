package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;

public class BaseInterpreter implements Interpreter {

    private List<BaseCommand> baseCommands = new LinkedList<>();

    public BaseInterpreter(final List<BaseCommand> baseCommands){
        this.baseCommands = baseCommands;
    }

    @Override
    public Command interpret(String toInterpretString) {
        // TODO potrebbe dare problemi nel test il fatto che non controlli se è presente l'opzionale.
        return baseCommands.stream().filter(p -> p.getKeyWord().equals(toInterpretString)).findAny().get();
    }

}
