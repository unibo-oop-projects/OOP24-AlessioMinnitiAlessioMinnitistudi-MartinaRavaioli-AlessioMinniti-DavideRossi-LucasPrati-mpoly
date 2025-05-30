package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

public class ParserOnSpaces implements Parser{

    private final String toParseString;
    private int index = 0;

    public ParserOnSpaces(final String str){
        this.toParseString = str; 
    }

    @Override
    public String next(){
        String ret=null; 
        for (int i = index; i < toParseString.length(); i++) {
            index = i + 2;
            if (toParseString.charAt(i) == 32) {
                
                return ret;
            }
            ret = ret + toParseString.charAt(i);
        }
        
        return ret;

    }
    
    @Override
    public boolean hasNesxt() {
        return index >= toParseString.length()-1;
    }

}
