package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public interface ArgsInterpreter {

    void interpret(String toInterpretString, BaseCommand command, Board board, TurnationManager turnM);
}
