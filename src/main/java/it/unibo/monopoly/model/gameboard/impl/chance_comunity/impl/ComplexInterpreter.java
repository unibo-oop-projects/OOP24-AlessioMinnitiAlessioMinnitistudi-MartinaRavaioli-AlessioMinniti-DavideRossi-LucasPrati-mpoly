package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommandFactory;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Interpreter;
import it.unibo.monopoly.model.transactions.api.Bank;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public class ComplexInterpreter implements Interpreter {
    
    private final BaseCommandFactory factory = new BaseCommandFactoryImpl();

    private final BaseInterpreter inter;


    public ComplexInterpreter(Board board, Bank bank){
        inter = new BaseInterpreter(factory.allCommand(bank, board));
    }

    @Override
    public Command interpret(String toInterpretString, Board board, TurnationManager turnM) {
        List<Command> commands = new LinkedList<>(); 
        ParserOnNewLine pars = new ParserOnNewLine(toInterpretString);
        while (pars.hasNesxt()) {
            commands.add(inter.interpret(pars.next(), board, turnM));            
        }
        Command comm = new ComplexCommand(commands, toInterpretString);
        return comm;
        
    }

}
