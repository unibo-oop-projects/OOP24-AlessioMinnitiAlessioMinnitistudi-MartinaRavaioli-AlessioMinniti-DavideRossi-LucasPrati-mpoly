package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

public class ParserOnHyphen implements Parser {

    private final String toParse;
    private String retString;
    private int index;

    public ParserOnHyphen(String toParseString){
        this.toParse = toParseString;
        index = 0;
    } 

    @Override
    public boolean hasNesxt() {
 
        if (index >= toParse.length()) {
            return false;         
        } else {
            String rStr = "";

            for (int i = index; i < toParse.length(); i++) {
                char c = toParse.charAt(i);
                index = i +2; 
                if ( c == 45) {
                    retString = rStr;
                    return true;
                } else {
                    rStr = rStr + c;
                }
            }
            retString = rStr; 
            return true;
        }
        
    }

    @Override
    public String next() {
        return retString;
    }

}
