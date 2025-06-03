package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

/**
 * implementation of args interpreter.
 */
public final class ArgsInterpreterImpl implements ArgsInterpreter {
    private static final int MIN_NUM = 48;
    private static final int MAX_NUM = 57;

    private String interpretTileArg(final String toInterpretString, final Board board) {
        Optional<String> t = null;
        t = board.getTiles().stream().filter(p -> p.getName().equals(toInterpretString)).map(p -> p.getName()).findAny();

        if (t.isPresent()) {
            return t.get();
        } else {
            return null;
        }
    }

    private int interpretIntArg(final String toInterpretString) {
        boolean validInt = true; 
        for (int i = 0; i < toInterpretString.length(); i++) {
            if (toInterpretString.charAt(i) < MIN_NUM || toInterpretString.charAt(i) > MAX_NUM) {
                validInt = false;
            }
        }

        if (validInt) {
            return Integer.valueOf(toInterpretString);
        } else {
            return -1;
        }
    }

    private List<Player> interpretPlayerArg(final String toInterpretString, final TurnationManager turnM) {

        List<Player> l = null;
        if ("all".equalsIgnoreCase(toInterpretString)) {
            l = turnM.getPlayerList();
        }
        return l;

    }

    @Override
    public void interpret(final String toInterpretString, final BaseCommand command, final Board board, final TurnationManager turnM) {
        final Parser p = new ParserOnComma(toInterpretString);
        while (p.hasNesxt()) {
            final String str = p.next();
            command.addIntArg(interpretIntArg(str));
            command.addPlayersArg(interpretPlayerArg(str, turnM));
            command.addTileArg(interpretTileArg(str, board));
        }
    }

}
