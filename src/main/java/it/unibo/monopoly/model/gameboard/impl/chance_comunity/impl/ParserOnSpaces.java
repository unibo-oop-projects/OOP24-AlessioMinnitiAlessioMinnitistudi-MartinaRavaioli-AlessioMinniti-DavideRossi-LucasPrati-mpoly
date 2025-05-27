package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

public class ParserOnSpaces {

    private final String toParseString;
    private int index = 0;

    public ParserOnSpaces(final String str){
        this.toParseString = str; 
    }

    public String next(){
        String ret=""; 
        for (int i = index; i < toParseString.length(); i++) {
            index = i + 1;
            if (toParseString.charAt(i) == 32) {
                return ret;
            }
            ret = ret + toParseString.charAt(i);
        }
        
        return ret;

    }

}
