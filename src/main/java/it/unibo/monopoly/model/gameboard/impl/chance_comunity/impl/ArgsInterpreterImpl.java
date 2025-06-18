package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.api.Tile;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.Parser;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of args interpreter.
 */
public final class ArgsInterpreterImpl implements ArgsInterpreter {

    private String interpretTileArg(final String toInterpretString, final Board board) {
        final String auxS = toInterpretString.replaceFirst(" ", "");
        final Optional<String> t = board.getTiles()
                                        .stream()
                                        .filter(p -> p.getName().equals(auxS))
                                        .map(Tile::getName)
                                        .findAny();

        if (t.isPresent()) {
            return t.get();
        } else {
            return null;
        }
    }

    private int interpretIntArg(final String toInterpretString) {
        try {
            final String auxS = toInterpretString.replace(" ", "");
            return Integer.parseInt(auxS);
        } catch (final NumberFormatException e) {
            return -1;
        }
    }

    private List<Player> interpretPlayerArg(final String toInterpretString, final TurnationManager turnM) {
        final String auxS = toInterpretString.replaceFirst(" ", "");
        List<Player> l = null;
        if ("all".equalsIgnoreCase(auxS)) {
            l = turnM.getPlayerList();
        }
        return l;

    }

    @Override
    public void interpret(final String toInterpretString, final BaseCommand command, 
                                        final Board board, final TurnationManager turnM) {
        final Parser p = new ParserOnComma(toInterpretString);
        while (p.hasNesxt()) {
            final String str = p.next();
            command.addIntArg(interpretIntArg(str));
            command.addPlayersArg(interpretPlayerArg(str, turnM));
            command.addTileArg(interpretTileArg(str, board));
        }
    }

}
