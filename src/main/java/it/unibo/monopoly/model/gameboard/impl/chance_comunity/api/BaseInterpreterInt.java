package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public interface BaseInterpreterInt extends Interpreter{

    @Override
    BaseCommand interpret(String toInterpretString, Board board, TurnationManager turnM);
}
