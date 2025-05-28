package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import java.util.List;

import it.unibo.monopoly.model.gameboard.api.Board;
import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.ArgsInterpreter;
import it.unibo.monopoly.model.turnation.api.Player;
import it.unibo.monopoly.model.turnation.api.TurnationManager;

public class ArgsInterpreterImpl implements ArgsInterpreter {

    @Override
    public String interpretTileArg(String toInterpretString, Board board) {
        String t = null;
        t = board.getTiles().stream().filter(p -> p.getName().equals(toInterpretString)).map(p -> p.getName()).findAny().get();
        return t; 
    }

    @Override
    public Integer interpretIntArg(String toInterpretString) {
        boolean validInt = true; 
        for (int i = 0; i < toInterpretString.length(); i++) {
            if (toInterpretString.charAt(i) < 48 || toInterpretString.charAt(i) > 57) {
                validInt = false;
            }
        }
        Integer num = null;
        if (validInt) {
            num = Integer.valueOf(toInterpretString);
        }
        return num; 
    }

    @Override
    public List<Player> interpretPlayerArg(String toInterpretString, TurnationManager turnM) {
        
        List<Player> l = null;
        if (toInterpretString.equalsIgnoreCase("all")) {
            l = turnM.getPlayerList();
        }
        return l;

    }

}
