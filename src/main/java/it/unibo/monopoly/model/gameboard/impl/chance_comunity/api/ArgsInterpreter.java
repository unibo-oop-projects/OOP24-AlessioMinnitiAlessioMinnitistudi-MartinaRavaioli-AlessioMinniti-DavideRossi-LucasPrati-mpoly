package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public interface ArgsInterpreter {

    String interpretTileArg(String toInterpretString, Board board);

    Integer interpretIntArg(String toInterpretString);

    List<Player> interpretPlayerArg(String toInterpretString, TurnationManager t);
}
