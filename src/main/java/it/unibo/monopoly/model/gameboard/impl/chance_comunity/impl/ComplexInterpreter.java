package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;
import it.unibo.monopoly.model.transactions.api.Bank;

public class ComplexInterpreter implements Interpreter {
    
    private final BaseCommandFactory factory = new BaseCommandFactoryImpl();

    private final BaseInterpreter inter;

    public ComplexInterpreter(Board board, Bank bank){
        inter = new BaseInterpreter(factory.allCommand(bank, board));
    }

    @Override
    public Command interpret(String toInterpretString) {
        return null;
        
    }

}
