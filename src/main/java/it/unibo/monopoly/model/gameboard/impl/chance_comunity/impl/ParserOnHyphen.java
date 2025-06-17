package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.api.chances_communiy.api.Parser;

/**
 * implementation of a parser on hypen, returns the strings before every hypen.
 */
public final class ParserOnHyphen implements Parser {

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
            final int hypen = 45;

            for (int i = index; i < toParse.length(); i++) {
                final char c = toParse.charAt(i);
                if (c == hypen) {
                    index = i + 2;
                    retString = rStr;
                    return true;
                } else {
                    rStr = rStr.concat(String.valueOf(c));
                }
            }
            index = toParse.length();
            retString = rStr; 
            return true;
        }
    }

    @Override
    public String next() {
        return retString;
    }

}
