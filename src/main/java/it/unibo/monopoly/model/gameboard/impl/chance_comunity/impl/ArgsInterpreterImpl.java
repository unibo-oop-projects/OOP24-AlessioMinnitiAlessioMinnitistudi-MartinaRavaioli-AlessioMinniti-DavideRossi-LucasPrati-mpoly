package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ArgsInterpreter;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.BaseCommand;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public  class ArgsInterpreterImpl implements ArgsInterpreter {

    private  String interpretTileArg(String toInterpretString, Board board) {
        Optional<String> t = null;
        t = board.getTiles().stream().filter(p -> p.getName().equals(toInterpretString)).map(p -> p.getName()).findAny();
        
        if (t.isPresent()) {
            return t.get();
        } else {
            return null;
        }
    }
    
    private  Integer interpretIntArg(String toInterpretString) {
        boolean validInt = true; 
        for (int i = 0; i < toInterpretString.length(); i++) {
            if (toInterpretString.charAt(i) < 48 || toInterpretString.charAt(i) > 57) {
                validInt = false;
            }
        }

        if (validInt) {
            return Integer.valueOf(toInterpretString);
        } else {
            return -1;
        }
        
    }

    private  List<Player> interpretPlayerArg(String toInterpretString, TurnationManager turnM) {
        
        List<Player> l = null;
        if (toInterpretString.equalsIgnoreCase("all")) {
            l = turnM.getPlayerList();
        }
        return l;

    }

    @Override
    public void interpret(String toInterpretString, BaseCommand command, Board board, TurnationManager turnM) {
        final Parser p = new ParserOnComma(toInterpretString);
        while (p.hasNesxt()) {
            final String str = p.next();
            command.addIntArg(interpretIntArg(str));
            command.addPlayersArg(interpretPlayerArg(str, turnM));
            command.addTileArg(interpretTileArg(str, board));
        }
        
    }

}
