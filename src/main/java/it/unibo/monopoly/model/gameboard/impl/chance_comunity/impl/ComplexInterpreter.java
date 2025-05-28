package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;

public class ComplexInterpreter implements Interpreter {
    
    private final BaseCommandFactory factory = new BaseCommandFactoryImpl();

    private final BaseInterpreter inter = new BaseInterpreter(factory.allCommand());

    @Override
    public Command interpret(String toInterpretString) {
        return null;
        
    }

}
