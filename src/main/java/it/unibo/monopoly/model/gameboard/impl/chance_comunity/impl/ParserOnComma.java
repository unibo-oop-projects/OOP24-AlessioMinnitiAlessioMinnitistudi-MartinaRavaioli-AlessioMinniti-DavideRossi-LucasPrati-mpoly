package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

public class ParserOnComma implements Parser{

    private final String toParseString;
    private int index = 0;

    public ParserOnComma(final String str){
        this.toParseString = str; 
    }

    @Override
    public String next(){
        String ret=""; 
        for (int i = index; i < toParseString.length(); i++) {
            index = i + 2;
            if (toParseString.charAt(i) == 44) {
                
                return ret;
            }
            ret = ret + toParseString.charAt(i);
        }
        
        return ret;

    }
    
    @Override
    public boolean hasNesxt() {
        return index < toParseString.length()-1;
    }

}
