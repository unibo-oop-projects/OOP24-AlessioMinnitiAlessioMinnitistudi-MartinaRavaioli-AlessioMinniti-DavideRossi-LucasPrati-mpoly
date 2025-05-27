package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;

public class ComplexInterpreter implements Interpreter {

    private final BaseInterpreter inter = new BaseInterpreter(null);

    @Override
    public Command interpret(String toInterpretString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interpret'");
    }

}
