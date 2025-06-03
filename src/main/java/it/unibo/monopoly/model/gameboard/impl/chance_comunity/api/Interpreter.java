package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * interface for a generic interpreter of string.
 */
public interface Interpreter {

    /**
     * this method interprets and transalte 
     * a String in a natural langage to a command object.
     * @param toInterpretStriing the string that must be translated
     * @param board to execute some of the commands
     * @param turnM to execute some of the commands
     * @return the command rapresented by that string
     */
    Command interpret(String toInterpretString, Board board, TurnationManager turnM);

}
