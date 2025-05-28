package it.unibo.monopoly.model.gameboard.impl.chance_comunity.api;

import it.unibo.monopoly.model.gameboard.api.Tile;

public interface ArgsInterpreter {

    Tile interpretTileArg(String toInterpretString);

    int interpretIntArg(String toInterpretString);
}
