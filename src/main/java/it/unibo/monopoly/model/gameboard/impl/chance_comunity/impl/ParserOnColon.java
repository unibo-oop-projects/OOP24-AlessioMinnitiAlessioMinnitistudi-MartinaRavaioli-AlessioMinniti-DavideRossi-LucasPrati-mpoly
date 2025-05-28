package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

public class ParserOnColon implements Parser{

    private final String toParseString;
    private int index = 0;

    public ParserOnColon(final String str){
        this.toParseString = str; 
    }

    @Override
    public String next() {
        String ret=null; 
        for (int i = index; i < toParseString.length(); i++) {
            index = i + 2;
            if (toParseString.charAt(i) == 58) {
                
                return ret;
            }
            ret = ret + toParseString.charAt(i);
        }
        
        return ret;
        
        
    }

}
