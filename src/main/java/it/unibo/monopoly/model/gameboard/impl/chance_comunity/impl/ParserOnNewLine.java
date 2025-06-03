package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

public class ParserOnNewLine implements Parser{
    private final String toParseString;
    private int index = 0;

    public ParserOnNewLine(final String str){
        this.toParseString = str; 
    }

    @Override
    public String next(){
        String ret=""; 
        for (int i = index; i < toParseString.length(); i++) {
            index = i + 1;
            if (toParseString.charAt(i) == 10) {
                return ret;
            }
            ret = ret + toParseString.charAt(i);
        }
        
        return ret;

    }

    @Override
    public boolean hasNesxt() {
        return index < toParseString.length();
    }

}
