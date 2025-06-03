package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Parser;

/**
 * implementation of a parser on hypen, returns the strings before every hypen.
 */
public final class ParserOnHyphen implements Parser {

    private final static int HYPEN = 45;
    private final String toParse;
    private String retString;
    private int index;

    /**
     * constructor.
     * @param toParseString the string to parse on
     */
    public ParserOnHyphen(final String toParseString) {
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
                index = i + 2; 
                if (c == HYPEN) {
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
